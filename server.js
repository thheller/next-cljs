
const express = require('express')
const next = require('next')
const dev = process.env.NODE_ENV !== 'production'
const port = parseInt(process.env.PORT, 10) || 3000
const app = next({ dir: './site', dev })
const handle = app.getRequestHandler()

app.prepare().then(_=>  {
  const server = express();

  // server.get('/', (req, res) => app.render(req, res, ''))
  server.get('/*', (req, res) => handle(req, res))

  server.listen(port, err => {
    if (err) throw err
    console.log(`> Read on http://localhost:${port}`)
  })
})