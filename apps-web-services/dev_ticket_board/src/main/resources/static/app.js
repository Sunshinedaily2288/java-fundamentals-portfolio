const API_URL = '/api/tickets';

document.addEventListener('DOMContentLoaded', fetchTickets);

async function fetchTickets() {
    try {
        const filterValue = document.getElementById('priorityFilter').value;
        let url = API_URL;

        // If a specific priority is chosen, use the filtering endpoint
        if (filterValue !== 'ALL') {
            url = `${API_URL}/priority/${filterValue}`;
        }

        const response = await fetch(url);
        const tickets = await response.json();

        document.getElementById('list-todo').innerHTML = '';
        document.getElementById('list-progress').innerHTML = '';
        document.getElementById('list-done').innerHTML = '';

        let counts = { TODO: 0, IN_PROGRESS: 0, DONE: 0 };

        tickets.forEach(ticket => {
            counts[ticket.status]++;
            const card = document.createElement('div');
            card.className = `ticket-card priority-${ticket.priority}`;

            // Build action buttons depending on where the card is sitting
            let actionButtons = '';
            if (ticket.status === 'TODO') {
                actionButtons = `<button style="background:#3b82f6;color:white;border:none;padding:4px 8px;border-radius:4px;cursor:pointer;font-size:11px;font-weight:bold;" onclick="moveTicket(${ticket.id}, 'IN_PROGRESS')">Start Work ▶</button>`;
            } else if (ticket.status === 'IN_PROGRESS') {
                actionButtons = `
                    <button style="background:#64748b;color:white;border:none;padding:4px 8px;border-radius:4px;cursor:pointer;font-size:11px;font-weight:bold;" onclick="moveTicket(${ticket.id}, 'TODO')">◀ Reject</button>
                    <button style="background:#10b981;color:white;border:none;padding:4px 8px;border-radius:4px;cursor:pointer;font-size:11px;font-weight:bold;" onclick="moveTicket(${ticket.id}, 'DONE')">Complete ▶</button>
                `;
            } else if (ticket.status === 'DONE') {
                actionButtons = `<button style="background:#f59e0b;color:white;border:none;padding:4px 8px;border-radius:4px;cursor:pointer;font-size:11px;font-weight:bold;" onclick="moveTicket(${ticket.id}, 'IN_PROGRESS')">◀ Reopen</button>`;
            }

            // 🟢 UPGRADED: ticket.assignedTo changed to ticket.assignedUser.name to extract nested relational data
            card.innerHTML = `
                <button class="btn-delete" onclick="deleteTicket(${ticket.id})">✕</button>
                <div class="ticket-title">${ticket.title}</div>
                <div class="ticket-desc">${ticket.description}</div>
                <div style="margin-bottom:10px; display:flex; gap:5px;">${actionButtons}</div>
                <div class="ticket-footer">
                    <span class="ticket-assignee">👤 ${ticket.assignedUser ? ticket.assignedUser.name : 'Unassigned'}</span>
                    <span class="priority-badge">${ticket.priority}</span>
                </div>
            `;

            if (ticket.status === 'TODO') document.getElementById('list-todo').appendChild(card);
            if (ticket.status === 'IN_PROGRESS') document.getElementById('list-progress').appendChild(card);
            if (ticket.status === 'DONE') document.getElementById('list-done').appendChild(card);
        });

        document.getElementById('count-todo').innerText = counts.TODO;
        document.getElementById('count-progress').innerText = counts.IN_PROGRESS;
        document.getElementById('count-done').innerText = counts.DONE;

    } catch (error) {
        console.error('Database connection routing exception:', error);
    }
}

async function moveTicket(id, nextStatus) {
    try {
        // Sends an SQL PATCH update request to transition status fields dynamically
        await fetch(`${API_URL}/${id}/status?status=${nextStatus}`, { method: 'PATCH' });
        fetchTickets();
    } catch (error) {
        console.error('Failed to patch ticket state:', error);
    }
}

async function saveTicket(event) {
    event.preventDefault();
    const newTicket = {
        title: document.getElementById('title').value,
        description: document.getElementById('description').value,
        assignedTo: document.getElementById('assignedTo').value,
        priority: document.getElementById('priority').value,
        status: document.getElementById('status').value
    };
    try {
        await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newTicket)
        });
        document.getElementById('ticketForm').reset();
        fetchTickets();
    } catch (error) {
        console.error('Failed to execute SQL insert:', error);
    }
}

async function deleteTicket(id) {
    if (confirm(`Are you sure you want to resolve and purge ticket #${id}?`)) {
        try {
            await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
            fetchTickets();
        } catch (error) {
            console.error('Failed to execute SQL delete:', error);
        }
    }
}
