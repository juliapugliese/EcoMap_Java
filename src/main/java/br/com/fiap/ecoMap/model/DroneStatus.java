package br.com.fiap.ecoMap.model;

public enum DroneStatus {
    ATIVO("Ativo"),
    INATIVO("Inativo"),
    EM_MANUTENCAO("Em manutenção");

    private String status;

    DroneStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return status;
    }
}
