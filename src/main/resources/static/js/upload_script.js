const fileInput = document.getElementById('myFile');
const fileLabel = document.getElementById('fileLabel');
fileInput.addEventListener('change', (e) => {
    fileInput.value = ''; // Сброс поля
    const fileInfo = e.target.files[0];
    fileLabel.textContent=fileInfo.name;
});