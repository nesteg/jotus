let stompClient = null;

const connect = () => {
  return new Promise((resolve, reject) => {
  stompClient = Stomp.over(new SockJS('/gs-guide-websocket'));
  stompClient.connect({}, (frame) => {
  console.log('Connected: ' + frame);
  stompClient.subscribe('/topic/response.users', (greeting) => {
             showUsers(JSON.parse(greeting.body).users)
          });
  stompClient.subscribe('/topic/response.user', (greeting) => {
            $("#userDataContainer").html(greeting.body);
         });
  resolve();
  })
  })
}

const sendMsg = (msg) => stompClient.send(msg)

const showUsers = (users) =>  {
   for (let user of users) {
       $("#listUsers").append("<tr><td>" + user.id + "</td>" + "<td>" + user.name + "</td></tr>")
   }
}


$(function () {

 $("#sendGet").click(()=>sendMsg("/app/message.getUser." + $("#userIdTextBox").val()));
 $("#sendSave").click(()=> {
             let userDto = {id:0,name:$("#userIdTextBox").val()}
             stompClient.send("/app/message.saveUser",{}, JSON.stringify(userDto));
        })
});
