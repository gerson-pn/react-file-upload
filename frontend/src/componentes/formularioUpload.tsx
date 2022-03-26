import 'materialize-css/dist/css/materialize.min.css'
import 'materialize-css/dist/js/materialize.min.js'
import React from 'react';
import { Component } from 'react';
import CarregadorArquivo from '../carregadores/carregadorArquivo';


class FormularioUpload extends Component {
    private arquivo: File
    constructor(props) {
        super(props)
        this.enviarArquivo = this.enviarArquivo.bind(this)
        this.receberArquivo = this.receberArquivo.bind(this)
    }


    public receberArquivo(evento) {
        this.arquivo = evento.target.files[0]
    }

    public enviarArquivo(evento) {
        evento.preventDefault()
        if(!(this.arquivo === undefined)){
            let carregador = new CarregadorArquivo()
            carregador.carregar(this.arquivo)
            evento.target.reset()
            this.arquivo = undefined
        }
    }

    render() {
        return (
            <div className="row">
                <div className="col s12">
                    <div className="card">
                        <div className="card-content">
                            <form onSubmit={(e) => this.enviarArquivo(e)} >
                                <div className="file-field input-field">
                                    <div className="btn light-blue darken-4">
                                        <span>Arquivo</span>
                                        <input type="file" onChange={(e) => this.receberArquivo(e)} />
                                    </div>
                                    <div className="file-path-wrapper">
                                        <input className="file-path validate" type="text" />
                                    </div>
                                </div>
                                <div>
                                    <button className="btn right waves-effect waves-light light-blue darken-4" type="submit" name="action">Enviar
                                        <i className="material-icons right">send</i>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}
export default FormularioUpload