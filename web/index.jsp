<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Item" %>
<%@ page import="manager.CategoryManager" %>
<%@ page import="manager.ItemManager" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.09.2022
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html lang="en">

<head>
    <%
        User user = (User) session.getAttribute("user");
        List<Item> itemList = (List<Item>) request.getAttribute("item");
        List<Category> categoryList = (List<Category>) request.getAttribute("category");
    %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap"
          rel="stylesheet">

    <title>MyItem</title>


    <!-- Additional CSS Files -->
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">

    <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">

    <link rel="stylesheet" href="assets/css/owl-carousel.css">

    <link rel="stylesheet" href="assets/css/lightbox.css">
    <!--


    -->
</head>

<body>

<!-- ***** Preloader Start ***** -->
<div id="preloader">
    <div class="jumper">
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>
<!-- ***** Preloader End ***** -->


<!-- ***** Header Area Start ***** -->
<header class="header-area header-sticky">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <!-- ***** Logo Start ***** -->
                    <a href="" class="logo">
                        <img src="">
                    </a>
                    <!-- ***** Logo End ***** -->
                    <!-- ***** Menu Start ***** -->
                    <ul class="nav">
                        <li class="scroll-to-section"><a href="#" class="active">Home</a></li>
                        <%for (Category category : categoryList) {%>
                        <li class=""><a href="/?cat_id<%=category.getId()%>"
                                        class="active"><%=category.getName()%>
                        </a></li>
                        <%}%>
                        <%
                            if (user != null) {
                        %>
                        <li class="scroll-to-section"><a href="/add/item">Add Advertisement</a></li>
                        <li class="scroll-to-section"><a href="/get/myItem">My Advertisement</a></li>
                        <li class="scroll-to-section"><a href="/add/item">|<%=user.getName()%>|
                        </a></li>
                        <li class="scroll-to-section"><a href="/logout">Logout</a></li>
                        <%} else {%>
                        <li class="scroll-to-section"><a href="/registr">Login | Registration</a></li>
                        <%}%>
                    </ul>
                    <a class='menu-trigger'>
                        <span>Menu</span>
                    </a>
                    <!-- ***** Menu End ***** -->
                </nav>
            </div>
        </div>
    </div>
</header>
<!-- ***** Header Area End ***** -->


<!-- ***** Ad Area Starts ***** -->
<section class="section" id="men">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <div class="section-heading">
                    <h1>Latest 20 Car's.</h1>
                </div>
            </div>
        </div>
    </div>
    <% for (Item itemes : itemList) {%>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="men-item-carousel">
                    <div class="owl-men-item owl-carousel">
                        <div class="item">
                            <div class="thumb">
                                <div class="hover-content">
                                    <ul>
                                        <li><a href="single-product.html"><i class="fa fa-eye"></i></a></li>
                                    </ul>
                                </div>
                                <img src="/getImage?profilePic=<%=itemes.getPicUrl()%>" width="100">
                            </div>
                            <div class="down-content">
                                <h4><%=itemes.getTitle()%>
                                </h4>
                                <span><%=itemes.getPrice()%></span>
                                <% }%>
                            </div>
                        </div>
</section>
<!-- ***** Ad Area Ends ***** -->
<form class="itemform" action="/add/item" method="post">

    <h1><strong>File upload</strong> with style and pure CSS</h1>

    <div class="form-group">
        <label for="title">Title <span>Use title case to get a better result</span></label>
        <input type="text" name="title" id="title" class="form-controll"/>
    </div>
    <div class="form-group">
        <label for="caption">Caption <span>This caption should be descriptiv</span></label>
        <input type="text" name="caption" id="caption" class="form-controll"/>
    </div>

    <div class="form-group file-area">
        <label for="images">Images <span>Your images should be at least 400x300 wide</span></label>
        <input type="file" name="images" id="images" required="required" multiple="multiple"/>
        <div class="file-dummy">
            <div class="success">Great, your files are selected. Keep on.</div>
            <div class="default">Please select some files</div>
        </div>
    </div>
    <link rel='stylesheet' href='style.css'>
    <div class="form-group">
        <button type="submit">Upload images</button>
    </div>
</form>
<!-- ***** Footer Start ***** -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="first-item">
                    <div class="logo">
                        <img src="">
                    </div>
                    <ul>
                        <li><a href="#">Armenia, Gyumri</a></li>
                        <li><a href="#">Myitem.am</a></li>
                        <li><a href="#">033-444-838</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-3">
                <h4>Post Ad's</h4>
                <ul>
                    <li><a href="#">Car’s</a></li>
                    <li><a href="#">Furniture’s</a></li>
                    <li><a href="#">Houses's</a></li>
                </ul>
            </div>
            <div class="col-lg-3">
                <h4>Useful Links</h4>
                <ul>
                    <li><a href="#">Homepage</a></li>
                    <li><a href="#">About Us</a></li>
                    <li><a href="#">Help</a></li>
                    <li><a href="#">Contact Us</a></li>
                </ul>
            </div>
            <div class="col-lg-12">
                <div class="under-footer">
                    <p>Copyright © 2022 MyItem Co., Ltd. All Rights Reserved.

                    <ul>
                        <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</footer>


<!-- jQuery -->
<script src="assets/js/jquery-2.1.0.min.js"></script>

<!-- Bootstrap -->
<script src="assets/js/popper.js"></script>
<script src="assets/js/bootstrap.min.js"></script>

<!-- Plugins -->
<script src="assets/js/owl-carousel.js"></script>
<script src="assets/js/accordions.js"></script>
<script src="assets/js/datepicker.js"></script>
<script src="assets/js/scrollreveal.min.js"></script>
<script src="assets/js/waypoints.min.js"></script>
<script src="assets/js/jquery.counterup.min.js"></script>
<script src="assets/js/imgfix.min.js"></script>
<script src="assets/js/slick.js"></script>
<script src="assets/js/lightbox.js"></script>
<script src="assets/js/isotope.js"></script>

<!-- Global Init -->
<script src="assets/js/custom.js"></script>

<script>

    $(function () {
        var selectedClass = "";
        $("p").click(function () {
            selectedClass = $(this).attr("data-rel");
            $("#portfolio").fadeTo(50, 0.1);
            $("#portfolio div").not("." + selectedClass).fadeOut();
            setTimeout(function () {
                $("." + selectedClass).fadeIn();
                $("#portfolio").fadeTo(50, 1);
            }, 500);

        });
    });

</script>

</body>
</html>

