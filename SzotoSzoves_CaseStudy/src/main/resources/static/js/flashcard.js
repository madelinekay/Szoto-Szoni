const data = [{code: "() => {}", question: "What type of function is this?", answer: "Is an anonymous function."}, {code: "var x = 5", question: "Is var recommended over const and let?", answer: "No. Unlike let/const var is function-scoped and not block-scoped. "}]

let flipCard = () => {
    console.log("clicked")
    let curr_code = document.getElementById("code").innerText;
    let current_content= document.getElementsByClassName("card-text")[0]
    let image = document.getElementById("card_image")
    for (let obj of data) {
        console.log(obj.code)
        if (obj.code == curr_code) {
            if (current_content.innerText == obj.question) {
                current_content.innerText = obj.answer
                image.src = "./resources/giphy.gif"
            } else {
                current_content.innerText = obj.question
                image.src = "./resources/computer.webp"
            }
        }
    }
}

let advanceCard = () => {
    let curr_code = document.getElementById("code");
    for (let i=0; i<data.length; i++) {
        if (data[i].code == curr_code.innerText) {
            if (i+1 < data.length) {
                curr_code.innerText = data[i+1].code;
                document.getElementsByClassName("card-text")[0].innerText = data[i+1].question
                return
            }
        }
    }
    alert("No more cards.")
}

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

let deleteWord = () => {
    console.log("here delete")
    const wordId = this.getAttribute('wordId');
    fetch(`/words/delete/${wordId}`), {
        method: 'DELETE',
    }
}