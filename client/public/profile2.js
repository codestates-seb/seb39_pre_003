
const qd_ask_buton = document.querySelector("#qd_ask_buton")
const postButton = document.querySelector(".postButton")


if(email) {
  qd_ask_buton.classList.remove('hide')
  postButton.classList.remove('hide')
}else {
  qd_ask_buton.classList.add('hide')
  postButton.classList.add('hide')
}

const handleLogout = () => {
  alert("로그아웃합니다.");
  localStorage.clear();
  location.href ="index.html"
};