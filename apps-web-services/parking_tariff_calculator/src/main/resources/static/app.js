const BASE_URL = '/api/parking';

document.addEventListener('DOMContentLoaded', () => {
    refreshTables();
});

async function refreshTables() {
    loadActiveVehicles();
    loadBillingHistory();
}

async function loadActiveVehicles() {
    try {
        const response = await fetch(`${BASE_URL}/active`);
        const activeTickets = await response.json();
        const tbody = document.getElementById('activeVehiclesTable');
        tbody.innerHTML = '';

        activeTickets.forEach(ticket => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>#${ticket.id}</td>
                <td style="font-weight:bold; letter-spacing:1px;">${ticket.licensePlate}</td>
                <td>${formatDateTime(ticket.entryTime)}</td>
                <td><span class="badge badge-active">PARKED</span></td>
                <td>
                    <button class="action-btn" onclick="checkoutVehicle(${ticket.id})">💳 Check Out</button>
                </td>
            `;
            tbody.appendChild(row);
        });
    } catch (error) {
        console.error("Failed to query active parking rows:", error);
    }
}

async function loadBillingHistory() {
    try {
        const response = await fetch(`${BASE_URL}/history`);
        const historyTickets = await response.json();
        const tbody = document.getElementById('historyTable');
        tbody.innerHTML = '';

        // Filter out records that haven't exited yet
        const completedTransactions = historyTickets.filter(ticket => ticket.exitTime !== null);

        completedTransactions.forEach(ticket => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>#${ticket.id}</td>
                <td style="font-weight:bold; letter-spacing:1px;">${ticket.licensePlate}</td>
                <td>${formatDateTime(ticket.entryTime)}</td>
                <td>${formatDateTime(ticket.exitTime)}</td>
                <td style="color:#10b981; font-weight:bold; font-size:15px;">€${ticket.calculatedFee.toFixed(2)}</td>
            `;
            tbody.appendChild(row);
        });
    } catch (error) {
        console.error("Failed to fetch historical session parameters:", error);
    }
}

async function registerEntry(event) {
    event.preventDefault();
    const plateInput = document.getElementById('licensePlate');
    const plate = plateInput.value.trim();

    try {
        await fetch(`${BASE_URL}/enter?licensePlate=${encodeURIComponent(plate)}`, {
            method: 'POST'
        });
        plateInput.value = '';
        refreshTables();
    } catch (error) {
        console.error("Failed to process entry lane request:", error);
    }
}

async function checkoutVehicle(id) {
    try {
        const response = await fetch(`${BASE_URL}/exit/${id}`, {
            method: 'POST'
        });
        const finalReceipt = await response.json();
        alert(`💳 Checkout Receipt Issued!\nVehicle: ${finalReceipt.licensePlate}\nTotal Tariff Charged: €${finalReceipt.calculatedFee.toFixed(2)}`);
        refreshTables();
    } catch (error) {
        console.error("Failed to commit exit checkout sequence:", error);
    }
}

function formatDateTime(dateTimeString) {
    if (!dateTimeString) return '-';
    const date = new Date(dateTimeString);
    return date.toLocaleString();
}
