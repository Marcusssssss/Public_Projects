<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">

    <div class="jumbotron">
        <h1>Booking App</h1>
        <p class="lead">If you're logged in as client, you can start booking.</p>
        <p><a href="Reservations.aspx" class="btn btn-primary btn-lg">Start Booking &raquo;</a></p>
    </div>

    <div class="row">
        <div class="col-md-6">
            <h2>Handle Reservations</h2>
            <p>
                Here you handle your reservations.
            </p>
            <p>
                <a class="btn btn-default" href="Reservations.aspx">Learn more &raquo;</a>
            </p>
        </div>
        <div class="col-md-6">
            <h2>Administrate hotels</h2>
            <p>
                If you're logged in as admin, enter to manage the registered rooms.
            </p>
            <p>
                <a class="btn btn-default" href="Administrate.aspx">Enter as Admin &raquo;</a>
            </p>
        </div>
    </div>
</asp:Content>
