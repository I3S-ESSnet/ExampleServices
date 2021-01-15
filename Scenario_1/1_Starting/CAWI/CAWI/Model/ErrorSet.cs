using System.Collections.Generic;
using Newtonsoft.Json;

namespace CAWI.Ui.Model
{
    public class ErrorSet
    {
        public IEnumerable<Error> Errors { get; set; }
    }
    
    public class Error
    {
        [JsonProperty(PropertyName = "error")]
        public string Value { get; set; }
        
        [JsonProperty(PropertyName = "errorNumber")]
        public string ErrorNumber { get; set; }
        
        [JsonProperty(PropertyName = "errorDescription")]
        public string ErrorDescription { get; set; }
    }
}