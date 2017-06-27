function required()  {  
	var empt1 = document.forms["form"]["nome"].value;
	var empt2 = document.forms["form"]["cognome"].value;  
	var empt3 = document.forms["form"]["email"].value;  
	var empt4 = document.forms["form"]["datadinascita"].value;  
	var empt5 = document.forms["form"]["password"].value;   
	if (empt1 == "" || empt2 == "" || empt3 == "" || empt4 == "" || empt5 == "")  {  
		alert("Completa tutti i campi con (*)");  
		return false;  
	}

	if(!document.forms["form"]["maschio"].checked && !document.forms["form"]["femmina"].checked) {
		alert("Completa tutti i campi con (*)");  
		return false;  
	}
}  