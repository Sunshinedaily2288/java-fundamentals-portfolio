package com.matharsa.dashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceDashboardApp extends JFrame {

    private JTextField hostField;
    private JButton refreshButton;
    private DefaultTableModel tableModel;
    private List<LocalService> serviceRegistry;

    static class LocalService {
        int port;
        String name;
        String endpointPath;

        public LocalService(int port, String name, String endpointPath) {
            this.port = port;
            this.name = name;
            this.endpointPath = endpointPath;
        }
    }

    public ServiceDashboardApp() {
        setTitle("com.matharsa // Microservice Mesh Status Dashboard");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        initializeServiceRegistry();

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.add(new JLabel("Target Host:"));
        hostField = new JTextField("localhost", 15);
        topPanel.add(hostField);

        refreshButton = new JButton("Refresh Status Mesh");
        topPanel.add(refreshButton);

        String[] columns = {"Port", "Service Module Name", "Endpoint", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable statusTable = new JTable(tableModel);
        statusTable.getColumnModel().getColumn(3).setCellRenderer(new StatusCellRenderer());
        JScrollPane scrollPane = new JScrollPane(statusTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Monitored Ports Matrix"));

        populateInitialTable();

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        refreshButton.addActionListener(e -> checkAllServices());
    }

    private void initializeServiceRegistry() {
        serviceRegistry = new ArrayList<>();
        serviceRegistry.add(new LocalService(8080, "library_app", "/"));
        serviceRegistry.add(new LocalService(8081, "online_library_gradle", "/"));
        serviceRegistry.add(new LocalService(8082, "inventory_sql_maven", "/"));
        serviceRegistry.add(new LocalService(8083, "snake_game (Desktop UI)", ""));
        serviceRegistry.add(new LocalService(8084, "banking_ledger_maven", "/"));
        serviceRegistry.add(new LocalService(8085, "ecommerce-catalog", "/"));
        serviceRegistry.add(new LocalService(8086, "security_crypto", "/api"));
        serviceRegistry.add(new LocalService(8087, "api_rate_limiter", "/api"));
        serviceRegistry.add(new LocalService(8088, "order_analytics", "/api"));
        serviceRegistry.add(new LocalService(8089, "parking_tariff_calculator", "/api"));
        serviceRegistry.add(new LocalService(8090, "security_audit_logger", "/api"));
        serviceRegistry.add(new LocalService(8091, "vending_engine_maven", "/api"));
        serviceRegistry.add(new LocalService(8092, "dev_ticket_board", "/index.html"));
        serviceRegistry.add(new LocalService(8093, "load_balancer_round_robin", "/"));
        serviceRegistry.add(new LocalService(8094, "inventory_caching_machine", "/"));
        serviceRegistry.add(new LocalService(8095, "system_metrics_gradle", "/api"));
        serviceRegistry.add(new LocalService(8096, "weather_stream_maven", "/"));
    }

    private void populateInitialTable() {
        tableModel.setRowCount(0);
        for (LocalService s : serviceRegistry) {
            String urlDisplay = s.endpointPath.isEmpty() ? "N/A (TCP)" : "http://localhost:" + s.port + s.endpointPath;
            tableModel.addRow(new Object[]{s.port, s.name, urlDisplay, "UNKNOWN"});
        }
    }

    private void checkAllServices() {
        refreshButton.setEnabled(false);
        String targetHost = hostField.getText().trim();

        ExecutorService executor = Executors.newFixedThreadPool(serviceRegistry.size());

        for (int i = 0; i < serviceRegistry.size(); i++) {
            final int rowIndex = i;
            LocalService service = serviceRegistry.get(i);

            executor.submit(() -> {
                String status = pingService(targetHost, service);
                SwingUtilities.invokeLater(() -> tableModel.setValueAt(status, rowIndex, 3));
            });
        }

        new Thread(() -> {
            executor.shutdown();
            try {
                executor.awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            SwingUtilities.invokeLater(() -> refreshButton.setEnabled(true));
        }).start();
    }

    private String pingService(String host, LocalService service) {
        if (service.endpointPath.isEmpty()) {
            try (java.net.Socket socket = new java.net.Socket()) {
                socket.connect(new java.net.InetSocketAddress(host, service.port), 150);
                return "ONLINE";
            } catch (IOException e) {
                return "OFFLINE";
            }
        }

        try {
            URL url = new URL("http://" + host + ":" + service.port + service.endpointPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(150);
            connection.setReadTimeout(150);

            int responseCode = connection.getResponseCode();
            if (responseCode >= 200 && responseCode < 400) {
                return "ONLINE";
            } else {
                return "ERROR (" + responseCode + ")";
            }
        } catch (IOException e) {
            return "OFFLINE";
        }
    }

    static class StatusCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            String status = (String) value;

            if ("ONLINE".equals(status)) {
                c.setForeground(new Color(0, 128, 0));
                setFont(getFont().deriveFont(Font.BOLD));
            } else if ("OFFLINE".equals(status)) {
                c.setForeground(Color.RED);
                setFont(getFont().deriveFont(Font.BOLD));
            } else {
                c.setForeground(Color.DARK_GRAY);
                setFont(getFont().deriveFont(Font.PLAIN));
            }
            return c;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ServiceDashboardApp().setVisible(true));
    }
}
