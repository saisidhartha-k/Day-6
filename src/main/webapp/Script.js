let pwd = document.querySelector('.password');
let confirmPwd = document.querySelector('.confirm-password')
let matchingTxt = document.querySelector('.matching-txt')
let form = document.querySelector('.form')

function comparePwd() {
    if (confirmPwd.value) {
    if (pwd.value != confirmPwd.value) {
       matchingTxt.style.display = 'block'
       matchingTxt.style.color = 'red'
       matchingTxt.innerHTML = 'Not Matching'
       return false
       e.preventDefault()

    } else {
        matchingTxt.style.display = 'block'
        matchingTxt.style.color = 'green'
        matchingTxt.innerHTML = 'Matching'
    }
} else {
    matchingTxt.style.display = 'none'
}
}
confirmPwd.addEventListener('keyup' , () => {
    comparePwd()
})

pwd.addEventListener('keyup' , () => {
    comparePwd()
})