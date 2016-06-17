$(document).ready(function(){
    $("#btn").on("click",function(){
        $("#slipdown").slideToggle();
    })
    $("#addfriend").on("click",function(){
        $("#addlist").slideToggle();
    })
    $(".cslid").on("mouseover",function(){
        $(this).css("background-color","darkgrey")
    })

    $(".cslid").on("mouseout",function(){
        $(this).css("background-color","lightgrey")
    })
    $("#pageli li>a>span").each(function(index){
        $(this).on("click",function(){
            $("#pageli li>a>span.sp1").removeClass("sp1");
            $(this).addClass("sp1");
            $("#menuli li.showli").removeClass("showli");
            $("#menuli li").eq(index).addClass("showli");
        })
    })
})
/**
 * Created by ENOVO on 2016/3/20.
 */


