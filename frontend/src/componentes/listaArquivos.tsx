/* eslint-disable jsx-a11y/anchor-is-valid */
import { Component } from "react";
import BuscadorArquivos from "../buscadores/buscadorArquivos";
import RemovedorArquivo from "../removedores/removedorArquivo";

type state = {
    arquivos: Object[]
}

class ListaArquivos extends Component<{}, state> {

    constructor(props) {
        super(props)
        this.buscarArquivos = this.buscarArquivos.bind(this)
        this.state = {
            arquivos: []
        }
    }

    public buscarArquivos() {
        let buscador = new BuscadorArquivos()
        const arquivos = buscador.buscar()
        arquivos.then(arquivos => {
            this.setState({ arquivos })
        })
    }

    public excluirArquivo(id: string, evento) {
        evento.preventDefault()
        let removedor = new RemovedorArquivo()
        removedor.remover(id)
    }

    componentDidMount(): void {
        setInterval(() => this.buscarArquivos(), 1000)
    }

    render() {
        let quantidadeArquivos = this.state.arquivos.length
        if (quantidadeArquivos < 1) {
            return (
                <></>
            )
        } else {
            let listaArquivos = this.state.arquivos.map(arquivo =>
                <div className="card horizontal" key={arquivo['id']}>
                    <div className="card-stacked">
                        <div className="card-content">
                            <div className="row">
                                <div className="col s1">
                                    <i className="material-icons small">insert_drive_file</i>
                                </div>
                                <div className="col s11">
                                    <p>Nome: {arquivo['nome']}<br />
                                        Tamanho: {arquivo['tamanho']} bytes<br />
                                        Tipo: {arquivo['tipo']}</p><br />
                                </div>
                            </div>
                            <a target={"_blank"} rel="noreferrer" href={arquivo['links'][0]['href']} className="btn btn-small halfway-fab waves-effect waves-light light-blue darken-4"><i className="material-icons">cloud_download</i></a>
                            <span> </span>
                            <span> </span>
                            <a onClick={(e) => this.excluirArquivo(arquivo['id'], e)} className="btn btn-small halfway-fab waves-effect waves-light light-blue darken-4"><i className="material-icons">block</i></a>
                        </div>
                    </div>
                </div>

            )
            return (
                <div className="row">
                    <div className="col s12">
                        {listaArquivos}
                    </div>
                </div>
            )
        }

    }
}
export default ListaArquivos