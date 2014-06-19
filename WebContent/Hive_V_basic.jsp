<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
  <head>
    <title>蜂巢0.1爬虫定制系统-系统配置-垂直爬虫-基本设置</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
        <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  
<header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav" role="banner">
	<div class="container">
    <div class="navbar-header">
      <a href="#" class="navbar-brand"><h4>蜂巢0.1爬虫定制系统</h4></a>
    </div>

      <ul class="nav navbar-nav">
      	<li>
      		<a href="#"><h4>首页</h4></a>
      	</li>
        <li class="active">
         	 <a href="#"><h4>系统配置</h4></a>
        </li>
        <li>
       	   <a href="#"><h4>抓取数据查看</h4></a>
		</li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li>
          <a href="#"><h4>文档下载</h4></a>
        </li>
      </ul>
	</div>

</header>
	<div class="container">
		<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<p>
			&nbsp;
			</p>
		</div>
	</div>
		<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<p>
			&nbsp;
			</p>
		</div>
	</div>
		<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<p>
			&nbsp;
			</p>
		</div>
	</div>
		<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<p>
			&nbsp;
			</p>
		</div>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-2">
					<div class="well col-md-2 sidebar-nav affix">
						<ul class="nav nav-pills nav-stacked">
							<li class="nav-header"><h4>垂直爬虫</h4></li>
							<li class="active"><a href="./HiveConfig_V_C_basic.html">基本设置</a></li>
							<li class="nav-header"><h4>搜索爬虫</h4></li>
							<li><a href="./HiveConfig_S_C_basic.html">基本设置</a></li>
							<li class="nav-header"><h4>通用</h4></li>
							<li><a href="./HiveConfig_visual.html">多维数据监测</a></li>
							<li><a href="#">开发者接口</a></li>
						</ul>
					</div>
				</div>

				<div class="col-md-6 col-md-offset-1">
					<div class="well col-md-6 sidebar-nav affix">
						<form id="Form1" method="post" action="hive_vb" role="form">
  							<div class="form-group">
    							<label for="seed1">请输入爬虫种子地址</label>
    							<input class="form-control" name="url" placeholder="Please input the address of web crawler">
  							</div>
  							<div class="form-group">
    							<label for="exampleInputEmail1">请输入爬取标签（后台数据会为标签单独建立文件夹以便后续数据开发,不同标签请以“分号”分割）</label>
    							<input class="form-control" name="tag" placeholder="Please input the tag">
  							</div>
  							<button type="submit" class="btn btn-primary">开始抓取</button>
  							<button class="btn btn-warning" data-toggle="modal" data-target="#Warning">停止抓取</button>
  							<button class="btn btn-danger" data-toggle="modal" data-target="#Danger">清除数据</button>
  						</form>

						<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>					

					</div>
				</div>
			</div>
		</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
	<script src="http://code.highcharts.com/modules/exporting.js"></script>    
	<script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
	<script src="http://cdn.bootcss.com/holder/2.0/holder.min.js"></script>
	<script src="http://cdn.bootcss.com/highlight.js/7.3/highlight.min.js"></script>

	<script type="text/javascript">
$(function () {
    $(document).ready(function() {
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });
    
        var chart;
        $('#container').highcharts({
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                    load: function() {
    
                        // set up the updating of the chart each second
                        var series = this.series[0];
                        setInterval(function() {
                            var x = (new Date()).getTime(), // current time
//                                y = Math.random();
//                            	y = 1;
								y = datas[0];
								datas.splice(0, 1);
                            series.addPoint([x, y], true, true);
                        }, 1000);
                    }
                }
            },
            title: {
                text: '每秒中爬虫抓取数据'
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: 'Value'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
                        Highcharts.numberFormat(this.y, 2);
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Random data',
                data: (function() {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;
    
                    for (i = -19; i <= 0; i++) {
                        data.push({
                            x: time + i * 1000,
                            y: 0
  //                          y: parseInt(document.getElementById('rand').value)
                        
                        });
                    }
                    return data;
                })()
            }]
        });
    });
    
});
var datas = [];
function yy(){
	$.ajax({
		type:"get",
		url:"hive_data",
		dataType:'json',
		data:{},
		success:function(data){
//			alert(data);
//			document.getElementById('rand').innerHTML = data;
			datas.push(data);
		}
	});
}
setInterval(yy, 1000);
</script>

<script type="text/javascript">
function ajaxSubmit(frm, fn) {
	var dataPara = getFormJson(frm);
	$.ajax({
	url: frm.action,
	type: frm.method,
	data: dataPara,
	success: fn
	});
	}
	//将form中的值转换为键值对。
	function getFormJson(frm) {
	var o = {};
	var a = $(frm).serializeArray();
	$.each(a, function () {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
	}
</script>

<script type="text/javascript">
$(document).ready(function(){
	$('#Form1').bind('submit', function(){
		ajaxSubmit(this, function(data){
		alert(data);
	});
	return false;
	});
});
</script>

  </body>
</html>

<!-- 
<input type="hidden" name="data" id="rand"/>
-->

<!-- Modal -->
<div id="Warning" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel"><center>暂停操作请慎重！</center></h3>
  </div>
  <div class="modal-body">
    <p><center><img src="../img/jinggao.jpg" width="350px"></center></p>
  </div>
  <div class="modal-footer">
    <button class="btn btn-warning" data-dismiss="modal" aria-hidden="true">执行</button>
    <button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
  </div>
  </div>
</div>

<div id="Danger" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel"><center>删除操作操作请慎重！</center></h3>
  </div>
  <div class="modal-body">
    <p><center><img src="../img/weixian.png" width="350px"></center></p>
  </div>
  <div class="modal-footer">
    <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">执行</button>
    <button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
  </div>
  </div>
</div>