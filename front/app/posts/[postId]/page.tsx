import Container from "@/app/components/Container";
import { Metadata } from "next";
import Image from "next/image";

async function getPost(id: number) {
  const res = await fetch(`http://localhost:4000/posts/${id}`, {
    next: { revalidate: 60 * 60 },
  });
  if (!res.ok) {
    throw new Error("Failed to fetch data");
  }
  return res.json();
}

export async function generateMetadata({
  params,
}: {
  params: {
    postId: number;
  };
}): Promise<Metadata> {
  const post = await getPost(params.postId);
  return { title: post.title };
}

export default async function Page({
  params,
}: {
  params: {
    postId: number;
  };
}) {
  const postId = params.postId;
  const post: Post = await getPost(postId);
  return (
    <Container>
      <h1>{post?.title}</h1>
      <Image
        alt={post?.title || "food image"}
        src={post?.imgUrl || ""}
        width={300}
        height={300}
      />
      <p>{post?.contents}</p>
      <p>{post?.author}</p>
      <p>{post?.createdAt}</p>
    </Container>
  );
}
