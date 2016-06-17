        var t1,t2,t3;
        var arr1 = new Array();
        arr1[0] = "不止";
        arr1[1] = "在手机，";
        arr1[2] = "也在电脑。";
        t1 = document.getElementById("text1");
        var newTime = 1000;
        var tetTime = 300;
        var newsi = 0;
        var txti = 0;
        var txttimer;   //调用setInterval的返回值，用于取消对函数的周期性执行
        var newstimer;
        function showNew(){
            var hwnewstr = arr1[newsi];
            if(txti>=hwnewstr.length){
                clearInterval(txttimer);  //一旦超过要显示的文字长度，清除对shownew()的周期性调用
                clearInterval(newstimer);
                newsi++;   //显示数组中的下一个
                document.getElementById("text1").innerHTML +="<br/>"
                if(newsi>=arr1.length){
                    newsi = 0;  //当newsi大于信息标题的数量时，把newsi清零，重新从第一个显示
                    return;
                }
                newstimer = setInterval("showNew()",newTime);   //间隔2000ms后重新调用shownew()
                txti = 0;
                return;
            }
            clearInterval(txttimer);
            document.getElementById("text1").innerHTML += hwnewstr.substring(txti,txti+1);   //截取字符，从第一个字符到txti+1个字符

            txti++;  //文字一个个出现
            txttimer = setInterval("showNew()",tetTime);
        }
        setTimeout("showNew()",7000);