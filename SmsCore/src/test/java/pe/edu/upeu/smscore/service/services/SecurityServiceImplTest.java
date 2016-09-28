package pe.edu.upeu.smscore.service.services;

import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.upeu.smscore.AbstractUnitTest;
import pe.edu.upeu.smscore.util.CommonUtils;

public class SecurityServiceImplTest extends AbstractUnitTest {

	@Autowired
	protected SecurityServiceImpl securityService;

	public void test() {
//		System.out.println(securityService);
//		System.out.println("hola " + securityService.findValidadUser("admision.juliaca@upeu.pe",
//				"15cRFpI8gaah3pq+M6cBykhw/lFbiofDyxnO0LO/zsw="));
		
		String cadena="Estimado ex-alumno de la EP de Sistemas UPeU-FJ, queda invitado al desfile y GRAN encuentro de egresados este jueves 22 de setiembre a la 1:00 pm en el nuevo comedor de nuestra Universidad. Ven y participa. Dios te cuide";
		int lengthValidate = 155;
		if(cadena.length()>lengthValidate){
			System.out.println(cadena.substring(0, lengthValidate));
			System.out.println(cadena.substring(lengthValidate, cadena.length()));			
		}
		
	}

	private static String[] splitByNumber(String text, int number) {

        int inLength = text.length();
        int arLength = inLength / number;
        int left=inLength%number;
        if(left>0){++arLength;}
        String ar[] = new String[arLength];
            String tempText=text;
            for (int x = 0; x < arLength; ++x) {

                if(tempText.length()>number){
                ar[x]=tempText.substring(0, number);
                tempText=tempText.substring(number);
                }else{
                    ar[x]=tempText;
                }

            }


        return ar;
    }

}
