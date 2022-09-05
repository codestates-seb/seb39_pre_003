const handleSignUp = () => {
    const check = document.getElementById("checkbox");
    const getRandomNumber = (min, max) => {
      return parseInt(Math.random() * (Number(max) - Number(min) + 2));
    };
    const signUp_request_body = {
      email: document.getElementById("email").value,
      name: document.getElementById("name").value,
      password: document.getElementById("password").value,
      profile_image: `https://picsum.photos/id/${getRandomNumber(1, 10)}/300/300`,
    };
    const singUp = async () => {
      await fetch(
        `http://ec2-15-165-63-80.ap-northeast-2.compute.amazonaws.com:8080/members/join`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(signUp_request_body),
        }
      )
        .then((res) => {
          if (res.status === 200 || res.status === 201) {
            return "회원가입이 완료되었습니다.";
          } else {
            //예시
            alert("이미 가입된..");
            return;
          }
        })
        .then((message) => {
          if (message === "회원가입이 완료되었습니다.") {
            alert(message + "\n로그인해주세요.");
            location.href = "login.html";
          }
        });
    };
  
    if (check.checked) {
      singUp();
    } else {
      //임의
      alert("광고성 메일 수신 여부를 체크해 주세요(필수)");
    }
  };