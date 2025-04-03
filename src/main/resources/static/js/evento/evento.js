let eventoModal;

function mostrarFormulario() {
    document.getElementById('eventoForm').reset();
    document.getElementById('eventoModalLabel').textContent = "Nuevo Evento";
    eventoModal.show();
}

document.addEventListener('DOMContentLoaded', () => {
    eventoModal = new bootstrap.Modal(document.getElementById('eventoModal'));
});