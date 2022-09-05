//boardMain에서 질문 타이틀 클릭시 로컬스토리지에 저장된 "key"의 value로 question_id 할당
const question_id = localStorage.getItem("key");
const url = "http://ec2-15-165-63-80.ap-northeast-2.compute.amazonaws.com:8080";

const getData = async (url, id) => {
  await fetch(`${url}/questions/${id}`)
    .then((res) => {
      if (!res.ok) {
        throw Error("could not fetch the data for that resource");
      }
      return res.json();
    })
    .then((data) => {
      const question_data = data;
      const answer_data = question_data.answers;
      let today = new Date();
      const getDateDiff = (d1, d2) => {
        const date1 = new Date(d1);
        const date2 = new Date(d2);
        const diffDate = date1.getTime() - date2.getTime();
        return Math.abs(diffDate / (1000 * 60 * 60 * 24));
      };

      //---- upper 질문 타이틀, 날짜, 뷰 등록 div

      //edit, delete 함수 구현
      const handleEdit = (data, target) => {
        if (target === "question") {
          localStorage.setItem("edit_target_data", JSON.stringify(data));
          location.href = "ask.html";
        }
        if (target === "answers") {
          alert("미구현..");
        }
      };
      const handleDelete = async (data, target) => {
        alert("삭제합니다.");
        let target_url = "";
        let url_id;
        if (target === "question") {
          target_url = "questions";
          url_id = data.questionId;
          localStorage.removeItem("key");
        } else {
          target_url = "answers";
          url_id = data.answerId + `?memberId=${data.memberId}`;
          location.href = "boardDetail.html";
        }
        await fetch(
          `http://ec2-15-165-63-80.ap-northeast-2.compute.amazonaws.com:8080/${target_url}/${url_id}`,
          {
            method: "DELETE",
            headers: {
              "Content-Type": "application/json",
            },
          }
        ).then((res) => {
          if (res.status === 200 || res.status === 204) {
            alert(`${target} deleted`);
            localStorage.removeItem("key");
            location.href = "boardMain.html";
          } else {
            alert("fail");
          }
        });
        //삭제 fetch 요청//
      };
      // --- edit, delete 함수 구현

      const question_vote = async (changer, target, data) => {
        const user = localStorage.getItem("memberId");
        let target_id;
        target === "question"
          ? (target_id = data.questionId)
          : (target_id = data.memberId);
        const vote_url = `http://ec2-15-165-63-80.ap-northeast-2.compute.amazonaws.com:8080/${target}s/${changer}/${target_id}?memberId=${user}`;
        await fetch(vote_url, {
          method: "PATCH",
          headers: {
            "Content-Type": "application/json",
          },
        }).then((res) => {
          if (res.status === 200) {
            window.location.reload();
          }
        });
      };

      //투표 컨테이터 및 컨테이너 렌더링 위한 함수 구현
      const makeVoteBar = (target, data, parent) => {
        const qd_lower_vote = document.createElement("div");
        qd_lower_vote.className = `qd_lower_vote qd_lower_${target}_vote`;
        const vote_up = document.createElement("i");
        vote_up.className = "fa-solid fa-caret-up fa-3x";
        vote_up.onclick = () => question_vote("upVote", target, data);
        const vote_span = document.createElement("span");
        vote_span.innerText = data.vote;
        const vote_down = document.createElement("i");
        vote_down.className = "fa-solid fa-caret-down fa-3x";
        vote_down.onclick = () => question_vote("downVote", target, data);
        qd_lower_vote.append(vote_up, vote_span, vote_down);
        parent.appendChild(qd_lower_vote);
      };

      const makeContent = (target, data, parent) => {
        const getMember = async (target, data, parent) => {
          await fetch(
            `http://ec2-15-165-63-80.ap-northeast-2.compute.amazonaws.com:8080/members/${data.memberId}`
          )
            .then((res) => {
              if (res.status === 200) {
                return res.json();
              }
            })
            .then((memberData) => {
              const qd_lower_content_wrapper = document.createElement("div");
              qd_lower_content_wrapper.className = `qd_lower_content_wrapper qd_lower_${target}_content_wrapper`;
              const qd_lower_content = document.createElement("div");
              qd_lower_content.className = "qd_lower_content";
              qd_lower_content.innerHTML = data.contents;
              qd_lower_content_wrapper.appendChild(qd_lower_content);

              if (target === "question" && data.questionTagNames !== "") {
                const qd_lower_tags = document.createElement("div");
                qd_lower_tags.className = "qd_lower_tags";
                const tags = data.questionTagNames.split(" ");
                for (let tag of tags) {
                  let qd_lower_tags_detail = document.createElement("a");
                  qd_lower_tags_detail.className = "qd_lower_tags_detail";
                  qd_lower_tags_detail.innerText = tag;
                  qd_lower_tags.appendChild(qd_lower_tags_detail);
                }
                qd_lower_content_wrapper.appendChild(qd_lower_tags);
              }

              const qd_lower_info = document.createElement("div");
              qd_lower_info.className = "qd_lower_info";
              const qd_lower_info_control = document.createElement("div");
              qd_lower_info_control.className = "qd_lower_info_control";
              let button_arr = ["Share", "Follow"];
              if (
                data.memberId === JSON.parse(localStorage.getItem("memberId"))
              ) {
                button_arr = ["Share", "Edit", "Delete", "Follow"];
              }
              for (let button of button_arr) {
                let qd_lower_info_control_button = document.createElement(
                  "a",
                  target
                );
                qd_lower_info_control_button.innerText = button;
                if (button === "Edit") {
                  qd_lower_info_control_button.onclick = () =>
                    handleEdit(data, target);
                }
                if (button === "Delete") {
                  qd_lower_info_control_button.onclick = () =>
                    handleDelete(data, target);
                }
                qd_lower_info_control.appendChild(qd_lower_info_control_button);
              }
              // const qd_lower_info_modified = document.createElement("div");
              // qd_lower_info_modified.className = "qd_lower_info_modified";
              // const qd_lower_info_modified_detail = document.createElement("a");
              // qd_lower_info_modified_detail.className =
              //   "qd_lower_info_modified_detail";
              // qd_lower_info_modified_detail.innerText = data.modified_at;
              // qd_lower_info_modified.appendChild(qd_lower_info_modified_detail);
              const qd_lower_info_users = document.createElement("div");
              qd_lower_info_users.className = "qd_lower_info_users";
              const qd_lower_info_users_time = document.createElement("div");
              qd_lower_info_users_time.className = "qd_lower_info_users_time";
              const qd_lower_info_users_time_check =
                document.createElement("span");
              data.modifiedDate === data.createdDate
                ? (qd_lower_info_users_time_check.innerText = "Asked  ")
                : (qd_lower_info_users_time_check.innerText = "Modified  ");

              const qd_lower_info_users_time_data =
                document.createElement("span");
              let date = data.modifiedDate.split("T");
              date[0] = date[0].split("-");
              date[1] = date[1].split(":");
              const month = [
                "Jan",
                "Feb",
                "Mar",
                "Apr",
                "May",
                "Jun",
                "Jul",
                "Aug",
                "Sep",
                "Oct",
                "Nov",
                "Dec",
              ];
              qd_lower_info_users_time_data.innerText = `${
                month[Number(date[0][1]) - 1]
              } ${date[0][2]}, ${date[0][0]} at ${date[1][0]}:${date[1][1]}`;
              qd_lower_info_users_time.append(
                qd_lower_info_users_time_check,
                qd_lower_info_users_time_data
              );
              const qd_lower_info_users_details = document.createElement("div");
              const qd_lower_info_users_details_img =
                document.createElement("img");
              const img = Math.floor(Math.random()*10+1)
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
              qd_lower_info_users_details_img.src = link;
              qd_lower_info_users_details_img.classList = "membericon"
              const qd_lower_info_users_details_name =
                document.createElement("a");
              qd_lower_info_users_details_name.innerText = memberData.name;
              qd_lower_info_users_details_name.className =
                "qd_lower_info_users_details_name";
              qd_lower_info_users_details.append(
                qd_lower_info_users_details_img,
                qd_lower_info_users_details_name
              );
              qd_lower_info_users.append(
                qd_lower_info_users_time,
                qd_lower_info_users_details
              );
              qd_lower_info.append(qd_lower_info_control, qd_lower_info_users);
              qd_lower_content_wrapper.appendChild(qd_lower_info);
              parent.appendChild(qd_lower_content_wrapper);
              return data;
            })
            .then(() => {});
        };
        getMember(target, data, parent);
      };

      const qd_question_title = document.getElementById("qd_question_title");
      qd_question_title.innerText = question_data.title;

      const qd_question_created_time = document.getElementById(
        "qd_question_created_time"
      );
      qd_question_created_time.datetime = question_data.createdDate;

      let diff = Math.floor(getDateDiff(question_data.createdDate, today));
      diff === 0
        ? (qd_question_created_time.innerText = "Today")
        : diff === 1
        ? (qd_question_created_time.innerText = "yesterday")
        : (qd_question_created_time.innerText = diff + " days ago");

      const qd_question_modified_time = document.getElementById(
        "qd_question_modified_time"
      );
      qd_question_modified_time.datetime = question_data.modifiedDate;
      diff = Math.floor(getDateDiff(question_data.modifiedDate, today));
      diff === 0
        ? (qd_question_modified_time.innerText = "Today")
        : diff === 1
        ? (qd_question_modified_time.innerText = "yesterday")
        : (qd_question_modified_time.innerText = diff + " days ago");

      const qd_question_view = document.getElementById("qd_question_view");
      const qd_question_view_span = document.createElement("span");
      qd_question_view_span.innerText = "Viewed";
      qd_question_view.appendChild(qd_question_view_span);
      qd_question_view.appendChild(document.createTextNode(question_data.view));
      const qd_lower_left_container = document.getElementById(
        "qd_lower_left_container"
      );
      //question 컨테이너 렌더링
      const qd_lower_question_container = document.createElement("div");
      qd_lower_question_container.className = "qd_lower_question_container";
      makeVoteBar("question", question_data, qd_lower_question_container);
      makeContent("question", question_data, qd_lower_question_container);
      qd_lower_left_container.appendChild(qd_lower_question_container);
      //answer 컨테이너 렌더링
      if (answer_data.length > 0) {
        const qd_lower_answer_container = document.createElement("div");
        qd_lower_answer_container.className = "qd_lower_answer_container";

        const qd_lower_answer_header = document.createElement("div");
        qd_lower_answer_header.className = "qd_lower_answer_header";
        const qd_lower_answer_header_main = document.createElement("div");
        qd_lower_answer_header_main.className = "qd_lower_answer_header_main";
        const qd_lower_answer_header_main_title = document.createElement("h2");
        qd_lower_answer_header_main_title.className =
          "qd_lower_title qd_lower_answer_header_main_title";
        qd_lower_answer_header_main_title.innerText = `${answer_data.length} Answers`;
        qd_lower_answer_header_main.appendChild(
          qd_lower_answer_header_main_title
        );
        qd_lower_answer_header.appendChild(qd_lower_answer_header_main);
        qd_lower_answer_container.appendChild(qd_lower_answer_header);
        //   const qd_lower_answer_header_filter = document.createElement("div");
        //   qd_lower_answer_header_filter.className = "qd_lower_answer_header_filter";

        for (let answer of answer_data) {
          const qd_lower_answer_wrapper = document.createElement("div");
          qd_lower_answer_wrapper.className = "qd_lower_answer_wrapper";
          makeVoteBar("answer", answer, qd_lower_answer_wrapper);
          makeContent("answer", answer, qd_lower_answer_wrapper);
          qd_lower_answer_container.appendChild(qd_lower_answer_wrapper);
        }
        qd_lower_left_container.appendChild(qd_lower_answer_container);
      }
      return [question_data, answer_data];
    })
    .then((x) => {
      const qd_lower_left_container = document.getElementById(
        "qd_lower_left_container"
      );
      const qd_lower_input_container = document.getElementById(
        "qd_lower_input_container"
      );
      qd_lower_left_container.appendChild(qd_lower_input_container);
    });
};
getData(url, question_id);

const setAnswer = async () => {
  const input_data = CKEDITOR.instances.editor1.getData();
  // question_id(선언할당되어있음), data 인풋내용, 사용자정보 바디를 포함해서 answer로 post요청
  const answer_body = {
    contents: input_data,
    memberId: localStorage.getItem("memberId"),
    questionId: localStorage.getItem("key"),
    title: "답글 제목입니다",
  };
  await fetch(
    "http://ec2-15-165-63-80.ap-northeast-2.compute.amazonaws.com:8080/answers/write",
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(answer_body),
    }
  )
    .then((res) => {
      if (res.status === 200 || res.status === 201) {
        return "답변 등록이 완료되었습니다.";
      } else {
        return "답변 등록 실패";
      }
    })
    .then((message) => {
      alert(message);
    });
};

const moveAsk = () => {
  location.href = "ask.html";
};

if (localStorage.getItem("memberId")) {
  const ask_button = document.getElementById("qd_ask_buton");
  ask_button.className = "btn";
} else {
  document.getElementById("qd_lower_input_container").style.visibility =
    "hidden";
}
