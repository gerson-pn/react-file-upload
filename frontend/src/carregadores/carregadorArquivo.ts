import { URI } from "../enumeracoes/uri";
import Carregador from "./carregador";

export default class CarregadorArquivo implements Carregador {
    carregar(arquivo: File): void {
        let dado = new FormData()
        dado.append('file', arquivo)
        fetch(URI.ENVIAR_ARQUIVO, {
            method: 'POST',
            body: dado
        })
    }
}