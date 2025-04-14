type Doc = {
  id: number | string
  title?: string
  content?: string
}

export default async function Page() {
  let docs
  try {
    const res = await fetch('http://localhost:3000/api/docs')
    docs = await res.json()
  } catch {
    docs = [{
      id: 1,
      title: "fallback",
      content: "fallback content"
    }]
  }

  return (
    <div>
      {docs.map((d: Doc) => (
        <p key={d.id}>{d.title}: {d.content}</p>
      ))}
    </div>
  )
}