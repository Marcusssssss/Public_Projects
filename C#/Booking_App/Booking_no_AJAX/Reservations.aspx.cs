using Microsoft.AspNet.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Reservations : System.Web.UI.Page
{
    BookingEntities db = new BookingEntities();
    protected void Page_Load(object sender, EventArgs e)
    {
        if (!User.Identity.IsAuthenticated)
            Response.Redirect("Account/Login.aspx");
        setSqlCommand();
    }

    private void setSqlCommand()
    {
        SqlDataSource1.SelectCommand =
                "select Res.Id as Id, Res.RId as [Room Id], Res.StartDate as [Start Date]," +
                 "Res.EndDate as [End Date], H.Name as [Hotel] " +
                "from Reservations Res " +
                "inner join Rooms R  on Res.RId = R.Id " +
                "inner join Hotels H on H.Id = R.HId " +
                "where Res.Cid = '" + Session["userid"] + "'";
        SqlDataSource1.DataBind();
        GridView1.DataBind();
    }


    protected void AddReservation(object sender, EventArgs e)
    {
        try
        {
            var reservation = new Reservation
            {
                CId = Session["userid"].ToString(),
                RId = Int32.Parse(roomid.SelectedValue),
                StartDate = DateTime.Parse(insertStart.Text),
                EndDate = DateTime.Parse(insertEnd.Text)
            };
            db.Reservations.Add(reservation);
            db.SaveChanges();
            ScriptManager.RegisterStartupScript(this, this.GetType(), "redirect",
            "alert('Reservation done!'); window.location='" +
            Request.ApplicationPath + "Reservations.aspx';", true);
        }
        catch (Exception ex)
        {
            ScriptManager.RegisterStartupScript(this, this.GetType(), "redirect",
            "alert('Reservation has not been registered:" + ex.Message + "'); window.location='" +
            Request.ApplicationPath + "Reservations.aspx';", true);
        }
    }
}