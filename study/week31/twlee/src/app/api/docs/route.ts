import { NextRequest } from "next/server";

export async function GET(req: NextRequest) {
  const url = new URL(req.url)
  const title = url.searchParams.get('title')

  const data = [
    {
      id: 1,
      title: `doc2`,
      content: "hello world1"
    },
    {
      id: 2,
      title: `doc2`,
      content: "hello world2"
    }
  ]

  return Response.json(data.filter((c) => title ? c.title.includes(title) : true ))
}