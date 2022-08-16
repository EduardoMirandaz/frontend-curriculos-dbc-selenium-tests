package util;


import util.Enums.TipoDeInvalidacao;

import java.util.UUID;

public class Geradores {

    String ramdomBaseString;
    public Geradores() {
        UUID uuid = UUID.randomUUID();
        ramdomBaseString = uuid.toString();
    }

    public String gerarLoginRandomico(){
        return ramdomBaseString.substring(0,15)+"@dbccompany.com.br";
    }

    public String gerarLoginRandomico(TipoDeInvalidacao tipoDeInvalidacao){
        if(tipoDeInvalidacao == TipoDeInvalidacao.EMAILSEMDOMINIO){
            return ramdomBaseString.substring(0,15);
        }
        else if(tipoDeInvalidacao == TipoDeInvalidacao.NULO){
            return null;
        }
        else if(tipoDeInvalidacao == TipoDeInvalidacao.SEMPONTO){
            return ramdomBaseString.substring(0,15)+"@dbccompanycombr";
        }
        else if(tipoDeInvalidacao == TipoDeInvalidacao.SOMENTESIMBOLOS){
            return "&%!*@¨#@***&@.@#!.(*#";
        }
        else if(tipoDeInvalidacao == TipoDeInvalidacao.SOMENTENUMEROS){
            return "21584685@235468.654.32";
        }
        else if(tipoDeInvalidacao == TipoDeInvalidacao.EMBRANCO){
            return "";
        }
        return null;
    }



    public String gerarSenhaRandomica(){
        return ramdomBaseString.substring(0,9);
    }

    public String gerarSenhaRandomica(TipoDeInvalidacao tipoDeInvalidacao){
        if(tipoDeInvalidacao == TipoDeInvalidacao.ABAIXO_TAMANHO_MINIMO){
            return ramdomBaseString.substring(0,7);
        }
        else if(tipoDeInvalidacao == TipoDeInvalidacao.NULO){
            return null;
        }
        else if(tipoDeInvalidacao == TipoDeInvalidacao.ACIMA_TAMANHO_MAXIMO){
            String substring1 = ramdomBaseString.substring(0, 36);
            String substring2 = ramdomBaseString.substring(0, 36);
            return substring1+substring2;
        }
        else if(tipoDeInvalidacao == TipoDeInvalidacao.SOMENTESIMBOLOS){
            return "&%!*@¨#@**";
        }
        else if(tipoDeInvalidacao == TipoDeInvalidacao.SOMENTENUMEROS){
            return "21584685";
        }
        else if(tipoDeInvalidacao == TipoDeInvalidacao.EMBRANCO){
            return "";
        }
        return null;
    }

}
