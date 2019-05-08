using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using System.IO;
using System.Web.Services;

namespace Termopane
{

    public class Produse
    {
        static string[] _words = { "Sam", "Dot", "Perls" };

        public static string GetDivElements()
        {
            // Initialize StringWriter instance.
            StringWriter stringWriter = new StringWriter();

            // Put HtmlTextWriter in using block because it needs to call Dispose.
            using (HtmlTextWriter writer = new HtmlTextWriter(stringWriter))
            {
                // Loop over some strings.
                foreach (var word in _words)
                {
                    /*
                     *   <div class="col-sm-6 col-md-4 col-xs-12 col-lg-3">
                        <div class="thumbnail">
                          <img src="..." alt="...">
                              <div class="caption">
                            <h3>Jaluzele orizontale</h3>
                            <p>...</p>
                            <p><a href="#" class="btn btn-primary" role="button">Detalii</a></p>
                          </div>
                        </div>
                      </div>
                     * */
                    // Some strings for the attributes.
                    //string classValue = "ClassName";
                    /* string urlValue = "Contact.aspx";
                     string imageValue = word.ToString()+".jpg";

                     // The important part:
                     writer.AddAttribute(HtmlTextWriterAttribute.Class, "col-sm-6 col-md-4 col-xs-12 col-lg-3");
                     writer.RenderBeginTag(HtmlTextWriterTag.Div); //Begin 1

                     writer.AddAttribute(HtmlTextWriterAttribute.Class, "thumbnail");
                     writer.RenderBeginTag(HtmlTextWriterTag.Div); //Begin 2

                     writer.AddAttribute(HtmlTextWriterAttribute.Href, "Contact.aspx");
                     writer.RenderBeginTag(HtmlTextWriterTag.A); ; //Begin 3

                     writer.AddAttribute(HtmlTextWriterAttribute.Src, imageValue);
                     writer.AddAttribute(HtmlTextWriterAttribute.Alt, word.ToString());
                     writer.RenderBeginTag(HtmlTextWriterTag.Img); //Begin 4
                     writer.RenderEndTag(); //End 4

                     writer.RenderEndTag(); //End 3

                     writer.AddAttribute(HtmlTextWriterAttribute.Class, "caption");
                     writer.RenderBeginTag(HtmlTextWriterTag.Div); //Begin 5

                     writer.RenderBeginTag(HtmlTextWriterTag.H3); //Begin 6
                     writer.Write(word);
                     writer.RenderEndTag(); //End 6

                     writer.RenderBeginTag(HtmlTextWriterTag.P); //Begin 7
                     writer.RenderEndTag(); //End 7

                     writer.AddAttribute(HtmlTextWriterAttribute.Href, "Contact.aspx");

                     writer.AddAttribute(HtmlTextWriterAttribute.Class, "btn btn-primary");
                     writer.AddAttribute();
                     writer.RenderBeginTag(HtmlTextWriterTag.P);
                     writer.RenderEndTag();


                     writer.RenderEndTag();
                     writer.RenderEndTag(); 
                     writer.RenderEndTag(); //End 2
                     writer.RenderEndTag(); //End 1
                     */
                }
            }
            // Return the result.
            //return stringWriter.ToString();
            return "Not yet";
        }
    }
}