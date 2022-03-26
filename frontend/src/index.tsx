import React from 'react';
import ReactDOM from 'react-dom';
import BarraNavegacao from './componentes/barraNavegacao';
import FormularioUpload from './componentes/formularioUpload';
import ListaArquivos from './componentes/listaArquivos';
import reportWebVitals from './reportWebVitals';

ReactDOM.render(
  <React.StrictMode>
    <BarraNavegacao titulo='Armazém de arquivos' />
    <FormularioUpload />
    <ListaArquivos />
  </React.StrictMode>,
  document.getElementById('root')
)

reportWebVitals();
