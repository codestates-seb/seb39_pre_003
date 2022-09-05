const handleLogin = async () => {
  const login_request_data = {
    email: document.getElementById("email").value,
    password: document.getElementById("password").value,
  };

  await fetch(
    `http://ec2-15-165-63-80.ap-northeast-2.compute.amazonaws.com:8080/members/login`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(login_request_data),
    }
  )
    .then((res) => {
      if (res.status === 200) {
        return res.json();
      } else {
        alert("아이디 패스워드 오류입니다.");
        return;
      }
    })
    .then((data) => {
      if (data === undefined) {
        return;
      } else {
        localStorage.setItem("memberId", data.memberId);
        localStorage.setItem("email", data.email);
        localStorage.setItem("name", data.name);
        localStorage.setItem("img", data.profile_image);
        location.href = "boardMain.html";
      }
    });
};

const handleLogout = () => {
  alert("로그아웃합니다.");
  localStorage.clear();
};


