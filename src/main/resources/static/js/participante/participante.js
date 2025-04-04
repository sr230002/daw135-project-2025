let participanteModal;

function mostrarFormulario() {
    document.getElementById('participanteForm').reset();
    document.getElementById('participanteModalLabel').textContent = "Nuevo Participante";
    participanteModal.show();
}

function cargarParticipante(registroId) {
    fetch(`participante/ver/${registroId}`)
        .then(response => response.json())
        .then(evento => {
            document.getElementById("participanteForm").action =  `${basePath}participantes/editar`;

            document.getElementById("eventoId").value = evento.eventoId;
            
            let modal = new bootstrap.Modal(document.getElementById("participanteModal"));
            modal.show();
        })
        .catch(error => console.error("Error al cargar el evento:", error));
}


document.addEventListener('DOMContentLoaded', () => {
    participanteModal = new bootstrap.Modal(document.getElementById('participanteModal'));
});