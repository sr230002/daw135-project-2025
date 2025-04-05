function mostrarToast(mensaje, tipo = "success", duracion = 2000) {
    const toastEl = document.getElementById('miToast');
    const toastMensaje = document.getElementById('toastMensaje');
    toastEl.classList.remove('bg-success', 'bg-danger', 'bg-warning', 'bg-info');
    toastEl.classList.add(`bg-${tipo}`);
    toastMensaje.textContent = mensaje;
    const toast = new bootstrap.Toast(toastEl, { delay: duracion });
    toast.show();
}

function showLoading(message = 'Procesando...') {
    document.getElementById('loadingText').textContent = message;
    const loadingModal = new bootstrap.Modal(document.getElementById('loadingModal'));
    loadingModal.show();
}

function hideLoading() {
    const loadingModal = bootstrap.Modal.getInstance(document.getElementById('loadingModal'));
    if (loadingModal) {
        loadingModal.hide();
    }
}