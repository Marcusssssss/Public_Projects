using Booking_no_AJAX;
using Microsoft.AspNet.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Booking : System.Web.UI.Page
{
    private static BookingEntities db = new BookingEntities();
    protected void Page_Load(object sender, EventArgs e)
    { 
        var um = new UserManager();
        string message = "";
        if (User.Identity.IsAuthenticated)
        {
            if (!um.IsInRole(User.Identity.GetUserId(), "Admin"))
                message = "You must be Admin to enter on this page";
        }
        else message = "Log in first";

        if (message != "")
            ScriptManager.RegisterStartupScript(this, this.GetType(), "redirect",
            "alert('" + message + "!'); window.location='" +
            Request.ApplicationPath + "Account/Login.aspx';", true);
    }

    protected void AddRoom(object sender, EventArgs e)
    {
        try
        {
            var room = new Room
            {
                Category = insertCategory.Text,
                Type = insertType.Text,
                Price = Int32.Parse(insertPrice.Text),
                HId = Int32.Parse(sqlhotel.SelectedValue)
            };

            db.Rooms.Add(room);
            db.SaveChanges();

            ScriptManager.RegisterStartupScript(this, this.GetType(), "redirect",
            "alert('Room inserted!'); window.location='" +
            Request.ApplicationPath + "Administrate.aspx';", true);
        }
        catch (Exception ex)
        {
            ScriptManager.RegisterStartupScript(this, this.GetType(), "redirect",
            "alert('Room was not inserted:" + ex.Message + "'); window.location='" +
            Request.ApplicationPath + "Administrate.aspx';", true);
        }
    }
}