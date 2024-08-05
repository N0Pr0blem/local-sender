const fileInput = document.getElementById('file');
const fileLabel = document.getElementById('fileLabel');
fileInput.addEventListener('change', (e) => {
    const fileInfo = e.target.files[0];
    fileLabel.textContent=fileInfo.name;
});