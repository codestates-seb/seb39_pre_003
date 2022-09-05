const email = localStorage.getItem("email")
const name3 = localStorage.getItem("name")
const img = localStorage.getItem("memberId")%10
const loginButton = document.querySelector("#loginButton")
const sighupButton = document.querySelector("#sighupButton")
const membericon = document.querySelector(".membericon")
const name2 = document.querySelector(".name")
const askQuestion = document.querySelector(".askQuestion")
var link = ''
const imgPhoto = () => {
  if(img === 1) {
    link = "https://firebasestorage.googleapis.com/v0/b/preproject-45ced.appspot.com/o/1.png?alt=media&token=39ab7cc6-72eb-4ee0-bbf3-c235f2b7e40e"
  }
  if(img === 2) {
    link = "https://firebasestorage.googleapis.com/v0/b/preproject-45ced.appspot.com/o/2.png?alt=media&token=0fe1ba04-e25b-4ab1-8a88-d85fd43e7378"
  }
  if(img === 3) {
    link = "https://firebasestorage.googleapis.com/v0/b/preproject-45ced.appspot.com/o/3.png?alt=media&token=ea283e11-59c9-444d-9c92-bb4dabf46ab1"
  }
  if(img === 4) {
    link = "https://firebasestorage.googleapis.com/v0/b/preproject-45ced.appspot.com/o/4.png?alt=media&token=77a4a2bc-687d-4625-9faa-5d2b8de9fd72"
  }
  if(img === 5) {
    link = "https://firebasestorage.googleapis.com/v0/b/preproject-45ced.appspot.com/o/5.png?alt=media&token=0b0a685e-b1c5-46a1-a38a-c7d34930ddc1"
  }
  if(img === 6) {
    link = "https://firebasestorage.googleapis.com/v0/b/preproject-45ced.appspot.com/o/6.png?alt=media&token=0902adf0-3d18-4c71-a1bf-e08e5459c35b"
  }
  if(img === 7) {
    link = "https://firebasestorage.googleapis.com/v0/b/preproject-45ced.appspot.com/o/7.png?alt=media&token=6b44165a-0830-4c96-aa23-97266e4a0e50"
  }
  if(img === 8) {
    link = "https://firebasestorage.googleapis.com/v0/b/preproject-45ced.appspot.com/o/8.png?alt=media&token=c9c546cb-b2cb-4732-ab33-642f163983bd"
  }
  if(img === 9) {
    link = "https://firebasestorage.googleapis.com/v0/b/preproject-45ced.appspot.com/o/9.png?alt=media&token=304d365f-c97d-4a46-8347-0305f40f2724"
  }
  if(img === 10) {
    link = "https://firebasestorage.googleapis.com/v0/b/preproject-45ced.appspot.com/o/10.png?alt=media&token=b281fb9f-4329-4388-b4e7-a7011c6134ac"
  }
}
imgPhoto()

if(email) {
  loginButton.classList.add('hide')
  sighupButton.classList.add('hide')
  membericon.classList.remove('hide')
  membericon.src = link
  name2.classList.remove('hide')
  name2.textContent = name3
  askQuestion.classList.remove('hide')
}else {
  loginButton.classList.remove('hide')
  sighupButton.classList.remove('hide')
  membericon.classList.add('hide')
  name2.classList.add('hide')
  askQuestion.classList.add('hide')
}

const handleLogout = () => {
  alert("로그아웃합니다.");
  localStorage.clear();
  location.href ="index.html"
};
