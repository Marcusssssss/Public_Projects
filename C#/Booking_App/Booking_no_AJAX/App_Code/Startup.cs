using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(Booking_no_AJAX.Startup))]
namespace Booking_no_AJAX
{
    public partial class Startup {
        public void Configuration(IAppBuilder app) {
            ConfigureAuth(app);
        }
    }
}
