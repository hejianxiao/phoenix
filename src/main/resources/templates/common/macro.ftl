[#ftl strip_whitespace=true]
[#global ctx="${springMacroRequestContext.contextPath}" /]

[#macro head]
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE-edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>后端管理系统</title>
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    [@commoncss /]
    [@commonjs /]
</head>
[/#macro]

[#macro commonjs]
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="${ctx}/js/jquery-my/jquery.override.js"></script>
<script src="${ctx}/js/jquery-my/jquery.share.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>

<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>

<script src="${ctx}/js/plugins/bootstrap-treeview.js"></script>
[/#macro]

[#macro commoncss]
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css">
<link rel="stylesheet" href="${ctx}/css/plugins/bootstrap-treeview.css">
[/#macro]

[#macro body]
    [@nav /]
    [@sidebar /]
[/#macro]

<!-- 顶部导航 -->
[#macro nav]
<nav class="navbar navbar-default navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">某管理系统</a>
        </div>


        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${ctx}/home">首页 <span class="sr-only">(current)</span></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">功能 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        [#if permissionList??]
                            [#list permissionList as parent]
                                [#if parent.url?? && parent.url != ""]
                                    <li role="separator" class="divider"></li>
                                    <li><a href="${ctx}${parent.url}">${parent.permissionName}</a></li>
                                [#else]
                                    <li role="separator" class="divider"></li>
                                    <li class="dropdown-header" >${parent.permissionName}</li>
                                    [#list parent.permissionList as child]
                                        <li><a href="${ctx}${child.url}">${child.permissionName}</a></li>
                                    [/#list]
                                [/#if]
                            [/#list]
                        [/#if]
                    </ul>
                </li>
                <li><a href="#">帮助</a></li>
            </ul>
            <form class="navbar-form navbar-left pull-right">
                <button type="button" class="btn btn-default btn-sm" onclick="logout()">安全退出</button>
            </form>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
[/#macro]
<!-- 侧边栏 -->
[#macro sidebar]
<div id="sidebar" class="col-xs-12 col-sm-2 sidebarContainer">
    <div id="tree">

    </div>
</div>
<script>
    function logout() {
        location.href = ctx + '/logout';
    }
    
    var width = document.documentElement.offsetWidth || document.body.offsetWidth ;
    if (width > 970) {
        $('#tree').treeview({
            data: '${(tree)!""}',
            enableLinks: true,
            onNodeChecked: function (event,data) {
            },
            onNodeSelected: function (event, data) {
            }
        });
    } else {
        $('#sidebar').hide();
    }
</script>
[/#macro]