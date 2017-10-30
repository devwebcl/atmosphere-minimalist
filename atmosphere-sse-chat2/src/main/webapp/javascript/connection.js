/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getConnection() {
    
    var ctx = $('#ctx').val();
    
    return {
        url: ctx + '/chat',
        contentType: "application/json",
        logLevel: 'debug',
        transport: 'sse',
        fallbackTransport: 'long-polling'
    };
}

function getSocket() {
    return atmosphere;
}
