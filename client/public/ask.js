const data = JSON.parse(localStorage.getItem("edit_target_data"));

if (data) {
  localStorage.removeItem("edit_target_data");
  console.log(data);
  const ask_title = document.getElementById("ask_title");
  ask_title.value = data.title;
  const editor1 = document.getElementById("editor1");
  editor1.value = data.contents;
  const ask_tag = document.getElementById("ask_tag");
  ask_tag.value = data.questionTagNames;
  const ask_submit_button =
    document.getElementsByClassName("ask_submit_button")[0];
  ask_submit_button.innerText = "Save edits";
  ask_submit_button.onclick = () => handleQuestionModify();
}

const handleQuestionSubmit = async () => {
  const ask_title = document.getElementById("ask_title").value;
  const ask_tag = document.getElementById("ask_tag").value;
  const ask_body = CKEDITOR.instances.editor1.getData();
  const ask_data = {
    contents: ask_body,
    memberId: localStorage.getItem("memberId"),
    questionTagNames: ask_tag,
    title: ask_title,
  };
  await fetch(
    "http://ec2-15-165-63-80.ap-northeast-2.compute.amazonaws.com:8080/questions/write",
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(ask_data),
    }
  )
    .then((res) => {
      if (res.status === 200 || res.status === 201) {
        return res.json();
      } else {
        return "질문 등록 실패";
      }
    })
    .then((data) => {
      if (data === "질문 등록 실패") {
        alert(data);
      } else {
        alert("질문을 등록하였습니다.");
        localStorage.setItem("key", data.questionId);
        location.href = "boardMain.html";
      }
    });
};

const handleQuestionModify = async () => {
  const ask_title = document.getElementById("ask_title").value;
  const ask_tag = document.getElementById("ask_tag").value;
  const ask_body = CKEDITOR.instances.editor1.getData();
  const ask_data = {
    contents: ask_body,
    memberId: localStorage.getItem("memberId"),
    questionId: data.questionId,
    title: ask_title,
  };
  await fetch(
    "http://ec2-15-165-63-80.ap-northeast-2.compute.amazonaws.com:8080/questions/modify",
    {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(ask_data),
    }
  )
    .then((res) => {
      if (res.status === 200 || res.status === 201) {
        return;
      } else {
        return "질문 수정 실패";
      }
    })
    .then((data) => {
      if (data === "질문 수정 실패") {
        alert(data);
      } else {
        location.href = "boardMain.html";
      }
    });
};
