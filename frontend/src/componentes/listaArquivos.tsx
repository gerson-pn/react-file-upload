/* eslint-disable jsx-a11y/anchor-is-valid */
import { Component } from "react";
import BuscadorArquivos from "../buscadores/buscadorArquivos";

type state = {
    arquivos: Object[]
}

class ListaArquivos extends Component<{}, state> {
    constructor(props) {
        super(props)
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

    componentDidMount(): void {
        this.buscarArquivos()
    }

    render() {
        let quantidadeArquivos = this.state.arquivos.length
        if (quantidadeArquivos < 1) {
            return (
                <></>
            )
        } else {
            let listaArquivos = this.state.arquivos.map(arquivo =>
                <li className="collection-item avatar " key={arquivo['id']}>
                    <i className="material-icons circle">insert_drive_file</i>
                    <span className="title">{arquivo['nome']}</span>
                    <p>
                        Tamanho: {arquivo['tamanho']} bytes
                        <br />
                        Tipo: {arquivo['tipo']}
                    </p>
                    <a className="secondary-content btn light-blue darken-4"><i className="material-icons ">block</i></a>
                    <a className="secondary-content btn light-blue darken-4"><i className="material-icons ">block</i></a>
                </li>

            )
            return (
                <div className="row">
                    <div className="col s12">
                        <ul className="collection">
                            {listaArquivos}
                        </ul>
                    </div>
                </div>
            )
        }

    }
}
export default ListaArquivos