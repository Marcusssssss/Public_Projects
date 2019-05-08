<%@ Page Title="Home" Language="C#" MasterPageFile="~/Home.Master" AutoEventWireup="true" CodeBehind="Home.aspx.cs" Inherits="Termopane.Home" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
          <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
          <li data-target="#myCarousel" data-slide-to="1"></li>
          <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
      
          <div class="item active">
            <img src="Imagini/image1.jpg" alt="PVC example" width="460" height="345">
          </div>
    
          <div class="item">
            <img src="Imagini/image2.jpg" alt="PVC example" width="460" height="345">
          </div>

          <div class="item">
            <img src="Imagini/image3.jpg" alt="PVC example" width="460" height="345">
          </div>
        </div>

        <br />

        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
          <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>
      <br /><br />

    <div class="row">
 <!-- <div class="col-sm-6 col-md-4 col-xs-12 col-lg-3">
    <div class="thumbnail">
      <img src="Imagini/Home/Usi din PVC.jpg" style="height:180px; width:200px;">
      <div class="caption">
        <h3>Usi din PVC</h3>
        <p>...</p>
        <p><a href="#" class="btn btn-primary" role="button">Detalii</a></p>
      </div>
    </div>
  </div>

        <div class="col-sm-6 col-md-4 col-xs-12 col-lg-3">
    <div class="thumbnail">
      <img src="Imagini/Home/Ferestre din PVC.jpg" style="height:180px; width:200px;">
      <div class="caption">
        <h3>Ferestre din PVC</h3>
        <p>...</p>
        <p><a href="#" class="btn btn-primary" role="button">Detalii</a></p>
      </div>
    </div>
  </div>

        <div class="col-sm-6 col-md-4 col-xs-12 col-lg-3">
    <div class="thumbnail">
      <img src="Imagini/Home/Jaluzele verticale.jpg" style="height:180px; width:200px;">
      <div class="caption">
        <h3>Jaluzele verticale</h3>
        <p>...</p>
        <p><a href="#" class="btn btn-primary" role="button">Detalii</a></p>
      </div>
    </div>
  </div>

        <div class="col-sm-6 col-md-4 col-xs-12 col-lg-3">
    <div class="thumbnail">
      <img src="Imagini/Home/Jaluzele orizontale.jpg" style="height:180px; width:200px;">
      <div class="caption">
        <h3>Jaluzele orizontale</h3>
        <p>...</p>
        <p><a href="#" class="btn btn-primary" role="button">Detalii</a></p>
      </div>
    </div>
  </div>

        <div class="col-sm-6 col-md-4 col-xs-12 col-lg-3">
    <div class="thumbnail">
      <img src="Imagini/Home/Plase de insecte.jpg" height="180" width="210">
      <div class="caption">
        <h3>Plase de insecte</h3>
        <p>...</p>
        <p><a href="#" class="btn btn-primary" role="button">Detalii</a></p>
      </div>
    </div>
  </div> -->
    <%
        Response.Write(produse_html());
    %>
</div>
    
</asp:Content>
