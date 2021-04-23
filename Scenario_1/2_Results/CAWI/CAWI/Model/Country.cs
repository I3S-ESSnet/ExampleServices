using Newtonsoft.Json;

namespace CAWI.Ui.Model
{
    public class Country
    {
        [JsonProperty("country")]
        public string Name { get; set; }
        [JsonProperty("code")]
        public string Code  {get; set; }
        
    }
}