        var newspan1 = document.createElement("span");
        var newspan2 = document.createElement("span");
        var newspan3 = document.createElement("span");
        var newspan4 = document.createElement("span");
        var newspan5 = document.createElement("span");
        function checkSign(){
            var inputEmail = document.getElementById("inputEmail1");
            var inputPassword = document.getElementById("inputPassword1");
            var inputPasswordagain = document.getElementById("inputPasswordagain");
            var inputNumber = document.getElementById("inputNumber");
            var inputUser = document.getElementById("inputUser1");
            var Eid =document.getElementById("Email_id");
            var uid = document.getElementById("User_id");
            var pid =document.getElementById("Pa_id");
            var cid = document.getElementById("Con_id");
            var nid = document.getElementById("Num_id");
            var count = 0;


            if(!checkEmail(inputEmail)){
                Eid.setAttribute("class","input-group  has-error has-feedback");
                newspan1.setAttribute("class","glyphicon glyphicon-remove form-control-feedback");
                Eid.appendChild(newspan1);
                inputEmail.onfocus = function(){
                    if(Eid.getAttribute("class") == "input-group  has-error has-feedback"){
                        inputEmail.value = "";
                        inputEmail.setAttribute("placeholder","请输入用户名小于30字符！");
                    }
                }
            }
            else{
                Eid.setAttribute("class","input-group  has-success has-feedback");
                newspan1.setAttribute("class","glyphicon glyphicon-ok form-control-feedback");
                Eid.appendChild(newspan1);
                count++;
            }
            if(!checkUser(inputUser)){
                uid.setAttribute("class","input-group  has-error has-feedback");
                newspan5.setAttribute("class","glyphicon glyphicon-remove form-control-feedback");
                uid.appendChild(newspan5);
                inputUser.onfocus = function(){
                    if(uid.getAttribute("class") == "input-group  has-error has-feedback"){
                        inputUser.value = "";
                        inputUser.setAttribute("placeholder","请输入昵称不大于10字符！");
                    }
                }
            }
            else{
                uid.setAttribute("class","input-group  has-success has-feedback");
                newspan5.setAttribute("class","glyphicon glyphicon-ok form-control-feedback");
                uid.appendChild(newspan5);
                count++;
            }
            if(!checkPassword(inputPassword)){
                pid.setAttribute("class","input-group  has-error has-feedback");
                newspan2.setAttribute("class","glyphicon glyphicon-remove form-control-feedback");
                pid.appendChild(newspan2);
                inputPassword.onfocus = function(){
                    if(pid.getAttribute("class") == "input-group  has-error has-feedback") {
                        inputPassword.value = "";
                        inputPassword.setAttribute("placeholder", "请输入密码不少于6字符！");
                    }
                }
            }
            else{
                pid.setAttribute("class","input-group  has-success has-feedback");
                newspan2.setAttribute("class","glyphicon glyphicon-ok form-control-feedback");
                pid.appendChild(newspan2);
                count++;
            }
            if(!checkPasswordAgain(inputPassword,inputPasswordagain)){
                cid.setAttribute("class","input-group  has-error has-feedback");
                newspan3.setAttribute("class","glyphicon glyphicon-remove form-control-feedback");
                cid.appendChild(newspan3);
                inputPasswordagain.onfocus = function(){
                    if(cid.getAttribute("class") == "input-group  has-error has-feedback") {
                        inputPasswordagain.value = "";
                        inputPasswordagain.setAttribute("placeholder", "两次密码输入不同！");
                    }
                }
            }
            else{
                cid.setAttribute("class","input-group  has-success has-feedback");
                newspan3.setAttribute("class","glyphicon glyphicon-ok form-control-feedback");
                cid.appendChild(newspan3);
                count++
            }
            if(!checkNumber(inputNumber)){
                nid.setAttribute("class","input-group  has-error has-feedback");
                newspan4.setAttribute("class","glyphicon glyphicon-remove form-control-feedback");
                nid.appendChild(newspan4);
                inputNumber.onfocus = function(){
                    if(nid.getAttribute("class") == "input-group  has-error has-feedback") {
                        inputNumber.value = "";
                        inputNumber.setAttribute("placeholder", "请输入11位的手机号！");
                    }
                }
            }
            else{
                nid.setAttribute("class","input-group  has-success has-feedback");
                newspan4.setAttribute("class","glyphicon glyphicon-ok form-control-feedback");
                nid.appendChild(newspan4);
                count++;
            }
            if(count == 5){
                $.ajax({
                    type:"POST",
                    url:"register.action",
                    data:"user.username="+$("#inputEmail1").val()+"&user.name="+$("#inputUser1").val()+"&user.password="+$("#inputPassword1").val()+"&user.phonenumber="+$("#inputNumber").val(),
                    success:function(msg){
                        alert("注册成功");
                    }
                });
                document.getElementById("close_id").click();
                document.getElementById("inputEmail3").value = inputEmail.value;
                document.getElementById("inputPassword3").value = inputPassword.value;
            }
        }



        function checkEmail(inputEmail){
            var strEmail = inputEmail.value.split("");
            if(strEmail.length>30 || strEmail.length ==0){
                return false;
            }
            else{
                return true;
            }
        }
        function checkUser(inputUser){
            var strUser = inputUser.value.split("");
            if(strUser.length>10 || strUser.length==0){
                return false;
            }
            else{
                return true;
            }
        }
        function checkPassword(inputPassword){
//            >=6
            var strPassword = inputPassword.value.split("");
            if(strPassword.length>=6){
                return true;
            }
            else{
                return false;
            }
        }
        function checkPasswordAgain(inputPassword,inputPasswordAgain){
            var strP1 = inputPassword.value;
            var strP2 = inputPasswordAgain.value;
            if(strP1 == strP2){
                return true;
            }
            else{
                return false;
            }
        }
        function checkNumber(inputNumber){
//            11
            var strNum = inputNumber.value.split("");
            if(strNum.length != 11){
                return false;
            }
            else{
                return true;
            }
        }