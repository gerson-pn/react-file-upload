import { URI } from "../enumeracoes/uri";
import Removedor from "./removedor";

export default class RemovedorArquivo implements Removedor{
    remover(idArquivo: string): void {
        let arquivo = {
            id: idArquivo
        }
        fetch(URI.EXCLUIR_ARQUIVO, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(arquivo)
        })
    }

}