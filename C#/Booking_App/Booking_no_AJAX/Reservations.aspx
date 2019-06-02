<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site.master" CodeFile="Reservations.aspx.cs" Inherits="Reservations" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <h3>Here handle your reservations!</h3>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {
            $(".datepicker").datepicker({ dateFormat: 'yy-mm-dd' });
        });
    </script>

    <div class="row">
        <div class="col-md-8 container">
            <h3>Available Rooms:
                <asp:GridView ID="GridView2" runat="server" AllowPaging="True" AutoGenerateColumns="False" BackColor="White" BorderColor="#CCCCCC" BorderStyle="None" BorderWidth="1px" CellPadding="4" DataKeyNames="Room Id" DataSourceID="SqlDataSource2" ForeColor="Black" GridLines="Horizontal" PageSize="4" Width="500px">
                    <Columns>
                        <asp:BoundField DataField="Room Id" HeaderText="Room Id" InsertVisible="False" ReadOnly="True" SortExpression="Room Id" />
                        <asp:BoundField DataField="Category" HeaderText="Category" SortExpression="Category" />
                        <asp:BoundField DataField="Type" HeaderText="Type" SortExpression="Type" />
                        <asp:BoundField DataField="Price" HeaderText="Price" SortExpression="Price" />
                        <asp:BoundField DataField="Hotel" HeaderText="Hotel" SortExpression="Hotel" />
                    </Columns>
                    <FooterStyle BackColor="#CCCC99" ForeColor="Black" />
                    <HeaderStyle BackColor="#333333" Font-Bold="True" ForeColor="White" />
                    <PagerStyle BackColor="White" ForeColor="Black" HorizontalAlign="Right" />
                    <SelectedRowStyle BackColor="#CC3333" Font-Bold="True" ForeColor="White" />
                    <SortedAscendingCellStyle BackColor="#F7F7F7" />
                    <SortedAscendingHeaderStyle BackColor="#4B4B4B" />
                    <SortedDescendingCellStyle BackColor="#E5E5E5" />
                    <SortedDescendingHeaderStyle BackColor="#242121" />
                </asp:GridView>
                <asp:SqlDataSource ID="SqlDataSource2" runat="server" ConnectionString="<%$ ConnectionStrings:DefaultConnection %>" SelectCommand="select R.Id as [Room Id],  R.Category as [Category], R.Type as [Type], R.Price as [Price], H.Name as [Hotel]
                                        from Rooms R 
                                        inner join Hotels H on R.HId=H.Id"></asp:SqlDataSource>
                <asp:EntityDataSource ID="EntityDataSource1" runat="server">
                </asp:EntityDataSource>

            </h3>
        </div>
        <div class="col-md-4 container">
            <div class="row">
                <h4>Add a reservation</h4>
                <div class="form-group col-md-10">
                    <label>Room Id:</label>
                    <asp:DropDownList CssClass="DropDownListStyling" DataValueField="Id" ID="roomid" runat="server" DataSourceID="sqlRooms" DataTextField="Id">
                    </asp:DropDownList>
                    <asp:SqlDataSource ID="sqlRooms" runat="server" ConnectionString="<%$ ConnectionStrings:DefaultConnection %>" SelectCommand="Select Id from Rooms"></asp:SqlDataSource>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-10">
                    <label for="insertStart">Start Date:</label>
                    <asp:TextBox runat="server" CssClass="datepicker form-control"  type="text" class="form-control" ID="insertStart" placeholder="Pick a Start Date" name="insertStart" />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="insertStart" CssClass="text-danger" ErrorMessage="The start date field is required." />
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-10">
                    <label for="insertEnd">End Date:</label>
                    <asp:TextBox runat="server" CssClass="datepicker form-control" type="text" class="form-control" ID="insertEnd" placeholder="Enter an End Date" name="insertEnd" />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="insertEnd" CssClass="text-danger" ErrorMessage="The end date field is required." />
                </div>
            </div>
            <br />
            <div class="row">
                <div class="form-group col-md-10">
                    <asp:Button runat="server" ID="btnAdd" type="submit" class="btn btn-outline-dark" Text="Add Reservation" OnClick="AddReservation" />
                </div>
            </div>

        </div>
    </div>

    <div class="row">
        <div class="offset-col-md-3 col-md-8 container">
            <h4>Your reservations:
                <asp:GridView ID="GridView1" runat="server" AllowPaging="True" AllowSorting="True" AutoGenerateColumns="False" AutoGenerateDeleteButton="True" BackColor="White" BorderColor="#CCCCCC" BorderStyle="None" BorderWidth="1px" CellPadding="4" DataKeyNames="Id" DataSourceID="SqlDataSource1" ForeColor="Black" GridLines="Horizontal" Height="161px" PageSize="4" Width="649px">
                    <Columns>
                        <asp:BoundField DataField="Id" HeaderText="Id" InsertVisible="False" Visible="false" ReadOnly="True" SortExpression="Id" />
                        <asp:BoundField DataField="Room Id" HeaderText="Room Id" SortExpression="Room Id" />
                        <asp:BoundField DataField="Start Date" HeaderText="Start Date" SortExpression="Start Date" />
                        <asp:BoundField DataField="End Date" HeaderText="End Date" SortExpression="End Date" />
                        <asp:BoundField DataField="Hotel" HeaderText="Hotel" SortExpression="Hotel" />
                    </Columns>
                    <FooterStyle BackColor="#CCCC99" ForeColor="Black" />
                    <HeaderStyle BackColor="#333333" Font-Bold="True" ForeColor="White" />
                    <PagerStyle BackColor="White" ForeColor="Black" HorizontalAlign="Right" />
                    <SelectedRowStyle BackColor="#CC3333" Font-Bold="True" ForeColor="White" />
                    <SortedAscendingCellStyle BackColor="#F7F7F7" />
                    <SortedAscendingHeaderStyle BackColor="#4B4B4B" />
                    <SortedDescendingCellStyle BackColor="#E5E5E5" />
                    <SortedDescendingHeaderStyle BackColor="#242121" />
                </asp:GridView>
                <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:DefaultConnection %>"
                    DeleteCommand="delete from Reservations where Id=@Id"></asp:SqlDataSource>
            </h4>
        </div>
    </div>


</asp:Content>
