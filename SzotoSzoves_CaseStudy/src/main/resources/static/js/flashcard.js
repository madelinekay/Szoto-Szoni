
let flipCard = () => {
    let flashcard = document.getElementById("flashcard");
    flashcard.classList.toggle('flipped');
    // let isFlipped = flashcard.getAttribute('data-isFlipped') == 'true';
    // let result = (!isFlipped).toString();
    // console.log("isFlipped: ", isFlipped);
    // console.log("result: ", result);
    // flashcard.setAttribute('data-isFlipped', result);
    //
    // let flashcardContent = document.getElementById('flashcard-text')
    // let translation = flashcard.getAttribute('data-back');
    // let word = flashcard.getAttribute('data-front');
    // flashcardContent.textContent = isFlipped ? translation : word;
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

let deleteWord = () => {
    console.log("here delete")
    const wordId = this.getAttribute('wordId');
    fetch(`/words/delete/${wordId}`), {
        method: 'DELETE',
    }
}