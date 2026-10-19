function validateForm() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const gender = document.querySelector('input[name="gender"]:checked');
    const styles = document.querySelectorAll('input[name="style"]:checked');
    const phone = document.getElementById('phone').value;
    const address = document.getElementById('address').value;

    if (!username) {
        alert('请输入用户名');
        return;
    }
    if (!password) {
        alert('请输入密码');
        return;
    }
    if (!gender) {
        alert('请选择性别');
        return;
    }
    if (styles.length === 0) {
        alert('请选择风格');
        return;
    }
    if (!phone) {
        alert('请输入手机号');
        return;
    }
    if (!address) {
        alert('请输入收货地址');
        return;
    }

    alert('注册成功');
}
