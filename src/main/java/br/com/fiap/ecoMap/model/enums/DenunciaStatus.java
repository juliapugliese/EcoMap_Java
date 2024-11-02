package br.com.fiap.ecoMap.model.enums;

public enum DenunciaStatus {
    EM_ANDAMENTO("Em andamento"),
    PENDENTE("Pendente"),
    CONCLUIDA("Concluída");

    private String status;

    DenunciaStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return status;
    }
}
