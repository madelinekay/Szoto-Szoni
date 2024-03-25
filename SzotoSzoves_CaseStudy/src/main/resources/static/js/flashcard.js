
let flipCard = () => {
    let flashcard = document.getElementById("flashcard");
    flashcard.classList.toggle('flipped');
}

// todo save words in the session
let getPreviousCard = () => {
    let curr_code = document.getElementById("code");
    console.log(curr_code.innerText)
    for (let i=0; i<data.length; i++) {
        if (data[i].code == curr_code.innerText) {
            if (i-1 >=0) {
                curr_code.innerText = data[i-1].code;
                document.getElementsByClassName("card-text")[0].innerText = data[i-1].question
                return
            }
        }
    }
    alert("No more cards.")
}

let validateForm = () => {
    const REGEX = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*]).{10,}$/;
    const password = document.getElementById("inputPassword");
    if (!REGEX.test(password.value)) {
        password.className += " is-invalid"
        alert("Password must be at least 10 characters and include 1 letter, 1 number and 1 special character.")
        return false
    }
}
