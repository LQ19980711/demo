<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Document</title>
    <style>
        @page {
            size: 250mm 320mm;
        }

        * {
            margin: 0;
            padding: 0;
        }

        ul {
            list-style: none;
            overflow: hidden;
            padding: 20px 20px 20px 20px;
        }

        html, body {
            width: 100%;
            height: 100%;
        }

        ul li {
            background: #fff;
            float: left;
            margin: 0 20px;
            padding: 10px;
            width: 200px;
            overflow: hidden;
        }

        .over_one_line {
            display: block;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }
    </style>
</head>
<!-- 能日赚30的手赚试玩平台，亲测有效 https://mp.weixin.qq.com/s?__biz=MzIyODgxNjkyOQ==&mid=100000040&idx=1&sn=47c0245f9dbe70f3ad6b2540209af2c2&chksm=684d60665f3ae97095ba07d8c6804bac4f55dbc6e7100fbb233945f65364df88682d41332eb7&xtrack=1&scene=0&subscene=10000&clicktime=1616647421&enterid=1616647421&ascene=7&devicetype=android-29&version=28000165&nettype=WIFI&abtest_cookie=AAACAA%3D%3D&lang=zh_CN&exportkey=AdxLWFyJlgtM6uFZCpgWtBk%3D&pass_ticket=G8rEeGdox4FPpICRkrKy6ho2QZozCzXi%2Be7gV5bXnQaXoZK2pw4S8Wf2j%2Bt3D8mi&wx_header=1 -->
<body style="font-family: SimSun;">
<ul>
    <#list labelList as label>
        <li>
            <img style="width: 200px;" src="${label.imgUrl}"/>
            <p class="over_one_line" style="padding-left: 10px">${label.name}</p>
        </li>
    </#list>
</ul>
</body>
</html>
