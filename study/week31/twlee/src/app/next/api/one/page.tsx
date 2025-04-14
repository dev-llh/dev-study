export default async function Page() {
  let doc
  try {
    const res = await fetch('http://localhost:3000/api/docs/2')
    doc = await res.json()
  } catch {
    doc = {
      id: 1,
      title: "fallback",
      content: "fallback content"
    }
  }

  return (
    <div>
      <p>{doc.title}: {doc.content}</p>
    </div>
  )
}