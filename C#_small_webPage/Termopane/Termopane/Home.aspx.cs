using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.Services;
using System.Data.SqlClient;

namespace Termopane
{
    public partial class Home : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            
        }

        public static string produse_html()
        {
            try
            {
                SqlConnection conn = new SqlConnection(@"Data Source = (LocalDB)\MSSQLLocalDB; AttachDbFilename=Termopane.mdf;Integrated Security = True");
                string outputcode="";
                conn.Open();
                SqlCommand comm = new SqlCommand("SELECT * FROM Produse");
                SqlDataReader reader = comm.ExecuteReader();
                while(reader.Read())
                {
                    outputcode += "<div class=\"col-sm-6 col-md-4 col-xs-12 col-lg-3\">"
                            + "<div class=\"thumbnail\">"
                                + "<img src = \"Imagini/Home/" + reader[1].ToString() + "\" height=\"180\" width=\"210\">"
                                + "<div class=\"caption\">"
                                + "<h3>" + reader[1].ToString() + "</h3>"
                                + "<p>" + reader[2].ToString() + "<p>"
                                + "<p><a href = \"" + reader[1].ToString() + ".aspx\" class=\"btn btn-primary\" role=\"button\">Mai multe</a></p>"
                                + "</div>"
                            + "</div>"
                        + "</div>";
                }
                conn.Close();
                return outputcode;
            }
            catch (Exception ex)
            {
                return ex.ToString();
            }
        }
    }
}