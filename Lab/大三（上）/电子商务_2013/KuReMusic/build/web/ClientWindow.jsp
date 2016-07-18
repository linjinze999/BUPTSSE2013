<%--
    Document   : ClientWindow
    Created on : 2015-12-18, 13:45:04
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.List, DB.MusicInformation"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>酷热音乐</title>
        <style>
            .body{width: 1340px; background-color:#e2efee}
            .mybody{width: 90%;height: 100%;margin-left: 5%; background-color:#8ec2f5;margin-top: 0px}
            
            .interface{width: 100%;height: 50px;margin-top: 30px;background-color: #267fe9;border-radius: 5px;}
            .interface div{background-color:#065fb9;width: 15%;height: 100%;margin-left: 2px;border-right: 1px skyblue solid;
                          margin-top: 0px;float: left;border-radius: 1px}
            .interface div:hover{background-color:#04477c;}
            
            .frame{width: 100%;height: 570px;margin-top: 0px;background-color:rgb(237,248,255) ;border-radius: 5px;}
            .frame_left{width: 38%;height: 100%;border: 1px white solid;float: left;margin-left: 2px;}
            .frame_left_name{width: 100%;height: 6%;border-radius: 3px;background-color: #267fe9;text-align: center;color: white;font-size: 25px;padding-top: 6px;}
            .frame_left_play{width: 100%;height: 19%;border: 1px white solid;margin-top: 5px;}
            .frame_left_mymusic{width: 100%;height: 70%;border: 1px black solid;background-color: white;overflow: scroll}
            .frame_left_mymusic_button{background-color: rgb(1,172,190);color: white;border-radius: 4px;}
            .frame_left_mymusic_button:hover{background-color: #04477c;cursor: pointer;}
            .frame_right{width: 61%;height: 100%;border: 1px white solid;float: right;margin-right: 2px;}
            .frame_right_name{width: 100%;height: 6%;border-radius: 3px;background-color: #267fe9;color: white;font-size: 25px;padding-top: 6px;}
            .frame_right_allmusic{width: 100%;height: 89%;border: 1px black solid;background-color: white;margin-top: 5px;overflow: scroll}
            .frame_right_allmusic_button{background-color: rgb(1,172,190);color: white;border-radius: 4px;}
            .frame_right_allmusic_button:hover{background-color: #04477c;cursor: pointer;}
            
            .audio_music_rate{width: 99%;height: 18px;}
            .audio_control{width: 100%;height: 98%;background-image: url(resource/images/clientwindow_audio_background.png);background-size: 100% 100%}
            .audio_control_play{width: 14%;height: 60%;float: left;border-radius: 30px;border:1px #267fe9 solid;margin-top: 10px;}
            .audio_control_play:hover{cursor: pointer;border:1px darkblue solid;}
            .audio_control_another{width: 40%;height: 25%;float: left;margin-top: 30px;margin-left: 5px;border: 2px #04477c solid;border-radius: 15px}
            .audio_control_another_a{float: left;margin-top: 6px;margin-left: 5px}
            .audio_control_last{width: 49%;height: 100%;float: left;border-radius: 15px 0px 0px 15px;}
            .audio_control_last:hover{background-color: window;cursor: pointer;}
            .audio_control_last_ima{background: url(resource/images/clientwindow_audio_last.png) no-repeat;width:20%;height: 60%;margin-top: 6px;margin-left: 15px;background-size: 100% 100%;float: left}
            .audio_control_next{width: 50%;height: 100%;float: right;border-radius: 0px 15px 15px 0px;border-left: 1px #065fb9 solid;}
            .audio_control_next:hover{background-color: window;cursor: pointer;}
            .audio_control_next_ima{background: url(resource/images/clientwindow_audio_next.png) no-repeat;width:20%;height: 60%;margin-top: 6px;margin-right: 15px;background-size: 100% 100%;float: right}
            .audio_control_volume{width: 25%;height: 40%;float: left;margin-left: 5px;margin-top: 5px;}
            .audio_control_volume a{width: 60%;float: left;text-align: center;}
            .audio_control_volume_sub{width: 20%;height: 40%;background: url(resource/images/clientwindow_audio_volume_sub.png) no-repeat;float: left;background-size: 100% 100%;}
            .audio_control_volume_sub:hover{cursor: pointer;background-color: #8ec2f5;}
            .audio_control_volume_add{width: 20%;height: 40%;background: url(resource/images/clientwindow_audio_volume_add.png) no-repeat;float: left;background-size: 100% 100%;}
            .audio_control_volume_add:hover{cursor: pointer;background-color: #8ec2f5;}
            .audio_control_loop_button{background-color: rgb(1,172,190);color: white;border-radius: 7px;width: 45%;height: 28px;font-size: 17px;}
            .audio_control_loop_button:hover{background-color: #065fb9;cursor: pointer;}
        </style>
    </head>
    <%!int playno=0;%>
    <%String clientid=request.getParameter("clientid");%>
    <%String clientpassword=request.getAttribute("clientpassword").toString();%>
    <%List<MusicInformation> myplaylist=(List<MusicInformation>)request.getAttribute("mymusic");%>
    <body class="body" onload="islogin()">
        <div class="mybody">
            <!--<div class="interface">
                <img src="" alt=""/>
                <div style="background-image: url(resource/images/logo.gif);background-size: 100% 100%;width: 10%"></div>
                <div></div>
                <div></div>
            </div>-->
            <audio id="audio" src="">
                <!--<source id="audio_music" style=" background-color: white" src="resource/music/Alex、夏天 - 多少次我告诫自己.mp3" type="audio/mpeg">-->
                Your browser does not support the <code>audio</code> tag.
            </audio>
            <div class="frame">
                <div class="frame_left">
                    <div class="frame_left_name">
                        欢迎您:<s:property value="#request.clientname"></s:property>
                    </div>
                    <div class="frame_left_play">
                        <div class="audio_music_rate">
                            <a id="playing_music_name" style="width: 77%;height: 100%;overflow: hidden;float: left;"></a>
                            <a style=" width: 23%;height: 100%;">
                                <a id="music_time_playing" style="width: 50%;overflow: hidden;border: 1px black solid"></a>
                                <a id="music_time_all" style="width: 50%;overflow: hidden;border: 1px black solid"></a>
                            </a>
                        </div>
                        <div class="audio_control">
                            <div class="audio_control_play" style="margin-left: 3px;">
                                <img id="audio_play" src="resource/images/clientwindow_audio_start.png" alt="" style=" width: 100%;height: 100%" 
                                     onclick="audio.play();MusicChange(mymusicbuttons[playno*2].value.substring(15));"/>
                                <img id="audio_pause" src="resource/images/clientwindow_audio_pause.png" alt="" style=" display: none;width: 100%;height: 100%"
                                     onclick="audio.pause();"/>
                            </div>
                            <div class="audio_control_play">
                                <img id="audio_stop_on" src="resource/images/clientwindow_audio_stop_on.png" alt="" style=" display: none;width: 100%;height: 100%"
                                     onclick="audio.pause();"/>
                                <img id="audio_stop_off" src="resource/images/clientwindow_audio_stop_off.png" alt="" style="width: 100%;height: 100%"/>
                            </div>
                            <div class="audio_control_another">
                                <div class="audio_control_last"  onclick="if(playno>0) playno-=1;else playno=mymusicbuttons.length/2;audio.src=mymusicbuttons[playno*2].value;audio.play();MusicChange(mymusicbuttons[playno*2].value.substring(15));">
                                    <div class="audio_control_last_ima"></div>
                                    <a class="audio_control_another_a">上一首</a>
                                </div>
                                <div class="audio_control_next" onclick="if(playno<mymusicbuttons.length/2)playno+=1;else playno=0;audio.src=mymusicbuttons[playno*2].value;audio.play();MusicChange(mymusicbuttons[playno*2].value.substring(15));">
                                    <a class="audio_control_another_a">下一首</a>
                                    <div class="audio_control_next_ima"></div>
                                </div>
                            </div>
                            <div class="audio_control_volume">
                                <div class="audio_control_volume_sub" onclick="audio.volume-=0.1;document.getElementById('volume').innerText='音量:'+audio.volume.toFixed(1);"></div>
                                <a id="volume">音量:1.0</a>
                                <div class="audio_control_volume_add" onclick="audio.volume+=0.1;document.getElementById('volume').innerText='音量:'+audio.volume.toFixed(1);"></div>
                                <div style="margin-top: 25px;">
                                    <button id="loop1" style=" background-color:#065fb9" class="audio_control_loop_button" 
                                            onclick="audio.loop=false;audio_loop=0;this.style.backgroundColor='#065fb9';document.getElementById('loop2').style.backgroundColor='rgb(1,172,190)';
                                            document.getElementById('loop3').style.backgroundColor='rgb(1,172,190)';document.getElementById('loop4').style.backgroundColor='rgb(1,172,190)';">
                                        单曲
                                    </button>
                                    <button id="loop2" class="audio_control_loop_button" 
                                            onclick="audio.loop=true;audio_loop=1;this.style.backgroundColor='#065fb9';document.getElementById('loop1').style.backgroundColor='rgb(1,172,190)';
                                            document.getElementById('loop3').style.backgroundColor='rgb(1,172,190)';document.getElementById('loop4').style.backgroundColor='rgb(1,172,190)';">
                                        单循
                                    </button>
                                    <button id="loop3" class="audio_control_loop_button" 
                                            onclick="audio.loop=false;audio_loop=2;this.style.backgroundColor='#065fb9';document.getElementById('loop1').style.backgroundColor='rgb(1,172,190)';
                                            document.getElementById('loop2').style.backgroundColor='rgb(1,172,190)';document.getElementById('loop4').style.backgroundColor='rgb(1,172,190)';">
                                        顺序
                                    </button>
                                    <button id="loop4" class="audio_control_loop_button" 
                                            onclick="audio.loop=false;audio_loop=3;this.style.backgroundColor='#065fb9';document.getElementById('loop1').style.backgroundColor='rgb(1,172,190)';
                                            document.getElementById('loop2').style.backgroundColor='rgb(1,172,190)';document.getElementById('loop3').style.backgroundColor='rgb(1,172,190)';">
                                        随机
                                    </button>
                                </div>
                            </div>
                            <div style=""></div>
                        </div>
                    </div>
                    <div class="frame_left_mymusic">
                        <table style="width: 100%;">
                        <s:iterator value="#request.mymusic" id="mymusics" status="st">
                                <tr>
                                    <td style=" width: 76%;border: 1px black solid">
                                        <s:property value="#st.count"/>
                                        <s:property value="#mymusics.getMUSICname()"/>
                                    </td>
                                    <td style=" width: 12%;border: 1px black solid">
                                        <button class="frame_left_mymusic_button" value='<s:property value="#mymusics.getMUSICurl()"/>' 
                                                onclick="audio.src=this.value;mymusic_play();playno='<s:property value="#st.count"/>'-1;
                                                MusicChange('<s:property value="#mymusics.getMUSICname()"/>')">
                                            播放
                                        </button>
                                    </td>
                                    <td style=" width: 12%;border: 1px black solid">
                                        <form action="remove" method="post">
                                            <input type="text" name="clientid" value="<%=clientid%>" hidden="true"/>
                                            <input type="text" name="clientpassword" value="<%=clientpassword%>" hidden="true"/>
                                            <input type="text" name="removeid" value='<s:property value="#mymusics.getMusicId()"/>' hidden="true"/>
                                            <input type="submit" class="frame_left_mymusic_button" value="移除"/>
                                        </form>
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                    </div>
                </div>
                <div class="frame_right">
                    <div class="frame_right_name">
                        <a style=" margin-left: 200px;">所有音乐</a>
                        <form style=" float: right;margin-right: 50px;" action="search" method="post">
                            <input type="text" name="clientid" value="<%=clientid%>" hidden="true"/>
                            <input type="text" name="clientpassword" value="<%=clientpassword%>" hidden="true"/>
                            <input type="Text" name="searchname" placeholder="&nbsp;搜空得到所有音乐"/>
                            <input type="submit" style="cursor: pointer;" value="搜索"/>
                        </form>
                    </div>
                    <div class="frame_right_allmusic">
                        <table style="width: 100%;">
                        <s:iterator value="#request.allmusic" id="allmusics" status="st">
                                <tr>
                                    <td style=" width: 84%;border: 1px black solid">
                                        <s:property value="#st.count"/>
                                        <s:property value="#allmusics.getMUSICname()"/>
                                    </td>
                                    <td style=" width: 8%;border: 1px black solid">
                                        <button class="frame_right_allmusic_button" value='<s:property value="#allmusics.getMUSICurl()"/>' 
                                                onclick="audio.src=this.value;mymusic_play();MusicChange('<s:property value="#allmusics.getMUSICname()"/>')">
                                            试听
                                        </button>
                                    </td>
                                    <td style=" width: 8%;border: 1px black solid">
                                        <form action="collect" method="post">
                                            <input type="text" name="clientid" value="<%=clientid%>" hidden="true"/>
                                            <input type="text" name="clientpassword" value="<%=clientpassword%>" hidden="true"/>
                                            <input type="text" name="collectid" value='<s:property value="#allmusics.getMusicId()"/>' hidden="true"/>
                                            <input type="submit" class="frame_right_allmusic_button" value="收藏"/>
                                        </form>
                                        <!--<button class="frame_right_allmusic_button" value='' 
                                                onclick="alert('I am writing...')">
                                            收藏
                                        </button>-->
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                    </div>
                </div>
            </div>
            
        </div>
        
        <script>
            var audio=document.getElementById('audio');
            var playno=0;
            var mymusicbuttons=document.getElementsByClassName('frame_left_mymusic_button');
            var audio_loop=0;
            var playing_music_name="";
            audio.src=mymusicbuttons[0].value;
            audio.addEventListener('ended', function(){//监听结束（播放下一首）
                if(audio_loop==2){
                    audio_next();
                }else if(audio_loop==3){
                    var Rand = Math.random();
                    playno=Math.round(Rand * (mymusicbuttons.length/2));
                    audio_replay();
                }
            }, false);
            audio.addEventListener('play', function(){//监听播放
                document.getElementById('audio_play').style.display='none';
                document.getElementById('audio_pause').style.display='block';
                document.getElementById('audio_stop_on').style.display='block';
                document.getElementById('audio_stop_off').style.display='none';
            }, false);
            audio.addEventListener('pause', function(){//监听暂停
                document.getElementById('audio_pause').style.display='none';
                document.getElementById('audio_play').style.display='block';
                document.getElementById('audio_stop_on').style.display='none';
                document.getElementById('audio_stop_off').style.display='block';
            }, false);
            audio.addEventListener("loadeddata", //监听加载
                function() {
                    setInterval(function() {
                        var currentTime = audio.currentTime;
                        timeChange(currentTime, "music_time_playing");
                    }, 1000);
                    var allTime =audio.duration;
                    timeChange(allTime, "music_time_all");
                }, false);
            function mymusic_play(){//播放
                audio.play();
            }
            function isLogin(){//检测登录
                if(<%=clientid%>==null){
                    window.location='LoginWindow.jsp';
                }
            }
            function audio_next(){//下一首
                if(playno<mymusicbuttons.length/2-1)
                    playno+=1;
                else
                    playno=0;
                audio_replay();
            }
            function audio_replay(){//重播
                audio.src=mymusicbuttons[playno*2].value;
                audio.play();
            };
            function MusicChange(name){//歌曲改变
                playing_music_name=name;
                document.getElementById('playing_music_name').innerText="正在播放："+playing_music_name;
            }
            //播放时间
            function timeChange(time, timePlace) {//默认获取的时间是时间戳改成我们常见的时间格式
                var timePlace = document.getElementById(timePlace);
                //分钟
                var minute = time / 60;
                var minutes = parseInt(minute);
                if (minutes < 10) {
                    minutes = "0" + minutes;
                }
                //秒
                var second = time % 60;
                seconds = parseInt(second);
                if (seconds < 10) {
                    seconds = "0" + seconds;
                }
                var allTime = "" + minutes + "" + ":" + "" + seconds + ""
                timePlace.innerHTML = allTime;
            }
        </script>
        
    </body>
</html>
