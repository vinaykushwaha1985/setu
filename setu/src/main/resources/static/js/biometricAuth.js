
let fingerDevicePort=new Map();
let irisDevicePort=new Map();


function registerDevice(xml,port) {

    var x,y, i, xmlDoc, info,status, method,path;
	var parser = new DOMParser();  
    xmlDoc = parser.parseFromString(xml, "text/xml");
    txt = "";

    x = xmlDoc.getElementsByTagName('RDService');
    for (i = 0; i < x.length; i++) {
		info = x[i].getAttribute('info');
        status = x[i].getAttribute('status');
if(status=='READY' && (info.includes('Iris') || info.includes('iris')))	{
	y = xmlDoc.getElementsByTagName('Interface');
    for (i = 0; i < y.length; i++) {
        method = y[i].getAttribute('id');
		path = y[i].getAttribute('path');
if(method=='CAPTURE'){
		irisDevicePort.set(port,path);
}
  }
	
}else if(status=='READY')	{
	y = xmlDoc.getElementsByTagName('Interface');
    for (i = 0; i < y.length; i++) {
        method = y[i].getAttribute('id');
		path = y[i].getAttribute('path');
	if(method=='CAPTURE'){
		fingerDevicePort.set(port,path);
	}
    }

    }
  } 

}



function portscan() {
				var port = 11100;
				
		for(i = port ; i <= 11106 ; i++){
				RDService(i);
			}
			offScan();
}

function RDService(port)
{

finalUrl = "http://127.0.0.1:"+port;
var verb = "RDSERVICE";
//alert(finalUrl);

var err = "";

var httpStaus = false;
var jsonstr="";

	$.ajax({

	type: "RDSERVICE",
	async: false,
	crossDomain: true,
	url: finalUrl,
	contentType: "text/xml; charset=utf-8",
	processData: false,
	success: function (data) {
	
		registerDevice(data,port);	
		
	}
});



}


function CaptureFingerAuth(valPort,valPath)
{


					var finalUrl ;
					if(valPath.includes("127.0.0.1:"))
					finalUrl = "http:/"+valPath;
					else
					finalUrl = "http://127.0.0.1:"+valPort+valPath;

			var PIDOPTS='<PidOptions ver=\"2.0\"> <Opts env=\"P\" fCount=\"1\" fType=\"0\" iCount=\"0\" iType=\"0\" pCount=\"0\" pType=\"0\" format=\"0\" pidVer=\"2.0\" timeout=\"10000\" otp=\"\" wadh=\"E0jzJ/P8UopUHAieZn8CKqS4WPMi5ZSYXgfnlfkWjrc=\" posh=\"UNKNOWN\"/> </PidOptions>'

							
						var verb = "CAPTURE";


                        var err = "";

						var res;
						$.support.cors = true;
						var httpStaus = false;
						var jsonstr="";
						;
							$.ajax({

							type: "CAPTURE",
							async: false,
							crossDomain: true,
							url: finalUrl,
							data:PIDOPTS,
							contentType: "text/xml; charset=utf-8",
							processData: false,
							success: function (data) {
							if(data.length>1000) {	
								$('#pidAuthData').val(data);
								document.getElementById("figerIdAuthSCapture").style.display = "block";
								document.getElementById("figerIdAuthFCapture").style.display = "none";
								
								document.getElementById("fingerASeccess").value="Y";
								document.getElementById("radioAI").disabled="true";
							 
							}else{
								document.getElementById("figerIdAuthSCapture").style.display = "none";
								document.getElementById("figerIdAuthFCapture").style.display = "block";
								alert("Biometric Capture failed, Please try again.");
							}
								
								
							}
						});


}


function CaptureIrisAuth(valPort,valPath)
{

					var finalUrl ;
					if(valPath.includes("127.0.0.1:"))
					finalUrl = "http:/"+valPath;
					else
					finalUrl = "http://127.0.0.1:"+valPort+valPath;

  
   var PIDOPTS="<PidOptions ver='1.0'> <Opts fCount='0' fType='0' iCount='1' pCount='0' pgCount='2' format='0'   pidVer='2.0' timeout='10000' pTimeout='20000' wadh='8QSrHOmcQhlyjiSpIgCi7o2ugs78w+4jhckNk1jeIJg=' posh='UNKNOWN' env='P' /> <CustOpts><Param name='mantrakey' value='' /></CustOpts> </PidOptions>";	
  
        
						var verb = "CAPTURE";


                        var err = "";

						var res;
						$.support.cors = true;
						var httpStaus = false;
						var jsonstr="";
						;
							$.ajax({

							type: "CAPTURE",
							async: false,
							crossDomain: true,
							url: finalUrl,
							data:PIDOPTS,
							contentType: "text/xml; charset=utf-8",
							processData: false,
							success: function (data) {
						
						if(data.length>1000) {	
							$('#pidAuthData').val(data);
							
							document.getElementById("irisIdAuthSCapture").style.display = "block";
							document.getElementById("irisIdAuthFCapture").style.display = "none";
							
							
							document.getElementById("irisAuthSeccess").value="Y";
							document.getElementById("radioAF").disabled="true";
								}else{

									document.getElementById("irisIdAuthSCapture").style.display = "none";
									document.getElementById("irisIdAuthFCapture").style.display = "block";
									alert("Biometric Capture failed, Please try again.");
								}
								
							}
						});
}


function captureBioAuth(device){
	
	
	var aadhar =document.getElementById("aadhar_auth").value;
	
	
	var aadhar =document.getElementById("aadhar_auth").value;
	
	if(aadhar==""){
		alert("Please Enter Aadhaar Number.");
		document.getElementById("aadhar_auth").focus();;
		return false;
	}
	
	
	if(device=='F'){
		for (var key of fingerDevicePort.keys()) {
			CaptureFingerAuth(key,fingerDevicePort.get(key));
   }
}
else if(device=='I'){
	for (var key of irisDevicePort.keys()) {
	CaptureIrisAuth(key,irisDevicePort.get(key));
   }

}
}

