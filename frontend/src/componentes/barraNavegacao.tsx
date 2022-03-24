/* eslint-disable jsx-a11y/anchor-is-valid */
// import M from 'materialize-css'
import 'materialize-css/dist/css/materialize.min.css'
import { Component } from 'react';

type props = {
    titulo: string
}

class BarraNavegacao extends Component<props> {
    render() {
        return (
            <nav>
                <div className="nav-wrapper light-blue darken-4">
                    <a className="brand-logo center">{this.props.titulo}</a>
                </div>
            </nav>
        )
    }
}
export default BarraNavegacao